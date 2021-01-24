package printers.duet.two;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import printers.duet.Duet;
import printers.duet.two.components.HeatedBed;
import printers.duet.two.components.Heater;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;

public class DuetTwo extends Duet {
    private String lastStatus = "";
    private String status = "Disconnected";
    private Heater[] heaters;
    private HeatedBed heatedBed;
    private float percentage = 0.0f;
    private final String name;
    private Runnable printTerminatedAction;

    public DuetTwo(@NotNull InetAddress address, String name){
        super(address);
        this.name = name;
    }

    private JSONObject fetchPrinterData(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        return new JSONObject(new String(is.readAllBytes()));
    }

    @Override
    public boolean connect(){
        try {
            JSONObject parsedData = fetchPrinterData("http://" + address.getHostAddress() + "/rr_status?type=1");
            JSONObject temps = parsedData.getJSONObject("temps");
            if(temps!=null){
                JSONObject bed = temps.getJSONObject("bed");
                if(bed!=null){
                    heatedBed = new HeatedBed("Bed", bed.getInt("heater"));
                    heatedBed.update(bed.getInt("active"), bed.getInt("current"), bed.getInt("standby"));
                }
                JSONObject tools = temps.getJSONObject("tools");
                if(tools!=null) {
                    JSONArray activeTools = tools.getJSONArray("active");
                    if (activeTools != null) {
                        heaters = new Heater[activeTools.length()];
                        for (int i = 0; i < heaters.length; i++) {
                            heaters[i] = new Heater("Heater", i+1);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void update() {
        try {
            JSONObject parsedData = fetchPrinterData("http://" + address.getHostAddress() + "/rr_status?type=3");

            String status = parsedData.getString("status");
            switch(status){
                case "F" -> this.status = "Updating";
                case "O" -> this.status = "Off";
                case "H" -> this.status = "Halted";
                case "D" -> this.status = "Pausing";
                case "S" -> this.status = "Paused";
                case "R" -> this.status = "Resuming";
                case "P" -> this.status = "Processing";
                case "M" -> this.status = "Simulating";
                case "B" -> this.status = "Busy";
                case "T" -> this.status = "Changing tool";
                case "I" -> this.status = "Idle";
                default -> this.status = "Error: unexpected status code";
            }


            JSONObject temps = parsedData.getJSONObject("temps");
            if(temps!=null){
                JSONObject bed = temps.getJSONObject("bed");
                if(bed!=null && heatedBed != null){
                    heatedBed.update(bed.getInt("active"), bed.getFloat("current"), bed.getInt("standby"));
                }
                JSONObject tools = temps.getJSONObject("tools");
                JSONArray current = temps.getJSONArray("current");
                if(tools!=null && current != null) {
                    JSONArray activeTools = tools.getJSONArray("active");
                    JSONArray standbyTools = tools.getJSONArray("standby");
                    if (activeTools != null && standbyTools!=null) {
                        for (int i = 0; i < heaters.length; i++) {
                            float targetTemperature = -1;
                            float standbyTemperature = -1;
                            float currentTempeature = -1;
                            if(i<activeTools.length()){
                                targetTemperature = activeTools.getJSONArray(i).getBigDecimal(0).floatValue();
                            }
                            if(i<standbyTools.length()){
                                standbyTemperature = standbyTools.getJSONArray(i).getBigDecimal(0).floatValue();
                            }
                            if(i+1<current.length()){
                                currentTempeature = current.getBigDecimal(i+1).floatValue();
                            }

                            heaters[i].update(targetTemperature, currentTempeature, standbyTemperature);
                        }
                    }
                }
            }
            this.percentage = parsedData.getFloat("fractionPrinted");

            // This is a little bit hacky, but should do the job
            if(this.percentage == 100 && !lastStatus.equals(status)){
                printTerminatedAction.run();
            }
            lastStatus = status;
        } catch(IOException | JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public @NotNull String getStatus() {
        return status;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public float getPercentagePrinted() {
        return percentage;
    }

    @Override
    public void disconnect() {

    }

    @Override
    public void onPrintTerminatedListener(Runnable runnable) {
        printTerminatedAction = runnable;
    }

    @Override
    public @NotNull Heater[] getHeaters() {
        return heaters;
    }

    @Override
    public @NotNull HeatedBed getHeatedBed() {
        return heatedBed;
    }

    @Override
    public String toString(){
        return getName();
    }
}
