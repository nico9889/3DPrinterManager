package gui;

import gui.panels.PrinterPanel;
import gui.panels.PrintersPanel;
import org.jetbrains.annotations.Nullable;
import printers.Manager;
import printers.Printer;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private final Manager manager;
    private @Nullable PrinterPanel selectedPrinterPanel;
    private final JPanel container;

    public Window(Manager manager){
        this.manager = manager;
        container = new JPanel(new GridLayout(0,2));
        container.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        if(manager.hasPrinters()) {
            selectedPrinterPanel = new PrinterPanel(manager.getSelectedPrinter());
            container.add(selectedPrinterPanel);
        }
        container.add(new PrintersPanel(manager, this));
        this.setTitle("3D Printer Manager");
        this.add(container);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void update() {
        if(selectedPrinterPanel!=null) {
            manager.update();
            selectedPrinterPanel.update();
        }
    }

    public void changeSelectedPrinter(Printer printer){
        if(selectedPrinterPanel!=null) {
            container.remove(selectedPrinterPanel);
        }
        manager.setSelectedPrinter(printer);
        selectedPrinterPanel = new PrinterPanel(printer);
        container.add(selectedPrinterPanel);
    }
}
