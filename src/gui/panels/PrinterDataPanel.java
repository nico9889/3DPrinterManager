package gui.panels;

import printers.Printer;

import javax.swing.*;
import java.awt.*;

public class PrinterDataPanel extends JPanel{
    private final JProgressBar progressBar;
    private final Printer printer;

    public PrinterDataPanel(Printer printer){
        super(new GridLayout(0,2));
        this.printer = printer;
        JLabel name = new JLabel("Status: ");
        JLabel status = new JLabel(printer.getStatus());
        JLabel progress = new JLabel("Progress: ");
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        this.add(name);
        this.add(status);
        this.add(progress);
        this.add(progressBar);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public void update(){
        progressBar.setValue((int)printer.getPercentagePrinted());
        progressBar.setString(printer.getPercentagePrinted() + "%");
    }
}
