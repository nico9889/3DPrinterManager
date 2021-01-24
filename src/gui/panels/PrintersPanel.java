package gui.panels;

import gui.Window;
import gui.window.AddPrinterWindow;
import printers.Manager;
import printers.Printer;

import javax.swing.*;

public class PrintersPanel extends JPanel {
    public final JButton addPrinterButton;

    public PrintersPanel(Manager manager, Window window) {
        addPrinterButton = new JButton("Add printer");
        DefaultListModel<Printer> listModel = new DefaultListModel<>();

        addPrinterButton.addActionListener((actionEvent) ->
                new AddPrinterWindow(manager, listModel)
        );


        listModel.addAll(manager.printers);
        JList<Printer> printerList = new JList<>(listModel);
        printerList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        printerList.setSize(printerList.getMaximumSize());
        JScrollPane listScroller = new JScrollPane(printerList);
        printerList.addListSelectionListener((selectionEvent) ->
                window.changeSelectedPrinter(printerList.getSelectedValue())
        );

        this.add(listScroller);
        this.add(addPrinterButton);
        this.setBorder(BorderFactory.createTitledBorder("Available printers"));
    }
}
