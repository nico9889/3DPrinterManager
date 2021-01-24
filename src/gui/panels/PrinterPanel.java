package gui.panels;

import printers.Printer;
import printers.components.BaseHeater;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PrinterPanel extends JPanel {
    private final ArrayList<HeaterDataPanel> heaterPanels = new ArrayList<>();
    private final PrinterDataPanel printerDataPanel;
    public PrinterPanel(Printer printer){
        super(new GridLayout(0,1));

        // Printer name and status
        printerDataPanel = new PrinterDataPanel(printer);
        this.add(printerDataPanel);

        // Printer heated bed data
        HeaterDataPanel bedDataPanel = new HeaterDataPanel(printer.getHeatedBed());
        heaterPanels.add(bedDataPanel);
        this.add(bedDataPanel);

        // Printer heaters data
        for(BaseHeater h:printer.getHeaters()){
            HeaterDataPanel heaterDataPanel = new HeaterDataPanel(h);
            heaterPanels.add(heaterDataPanel);
            this.add(heaterDataPanel);
        }
        this.setBorder(BorderFactory.createTitledBorder(printer.getName()));
    }

    public void update() {
        printerDataPanel.update();
        for(HeaterDataPanel hdp:heaterPanels){
            hdp.update();
        }
    }
}
