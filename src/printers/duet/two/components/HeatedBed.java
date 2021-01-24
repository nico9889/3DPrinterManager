package printers.duet.two.components;

import org.jetbrains.annotations.NotNull;
import printers.components.BaseHeater;

public class HeatedBed extends BaseHeater {

    public HeatedBed(@NotNull String name, int number) {
        super(name, number);
    }

    @Override
    public float getTargetTemperature() {
        return targetTemperature;
    }

    @Override
    public void setTargetTemperature(float targetTemperature) {

    }

    @Override
    public float getTemperature() {
        return temperature;
    }

    @Override
    public float getStandbyTempeature() {
        return standbyTemperature;
    }
}
