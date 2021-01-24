package printers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Manager {
    private @Nullable Printer selectedPrinter;
    public final ArrayList<Printer> printers;

    public Manager(ArrayList<Printer> printers){
        this.printers = printers;
        if(!printers.isEmpty()) {
            selectedPrinter = printers.get(0);
            setTerminationListener();
            selectedPrinter.connect();
            selectedPrinter.update();
        }
    }

    public void update(){
        if(selectedPrinter != null) {
            selectedPrinter.update();
        }
    }

    public @Nullable Printer getSelectedPrinter(){
        return selectedPrinter;
    }

    private void setTerminationListener(){
        if(selectedPrinter!=null) {
            selectedPrinter.onPrintTerminatedListener(() -> {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Printed terminated!");
            });
        }
    }

    public void setSelectedPrinter(@NotNull Printer printer){
        if(selectedPrinter!=null) {
            selectedPrinter.disconnect();
        }
        this.selectedPrinter = printer;
        selectedPrinter.connect();
        selectedPrinter.update();
        setTerminationListener();
    }

    public void addPrinter(@NotNull Printer printer){
        printers.add(printer);
        if(printers.size() == 1){
            selectedPrinter = printer;
            setTerminationListener();
            selectedPrinter.connect();
            selectedPrinter.update();
        }
    }

    public boolean hasPrinters() {
        return !printers.isEmpty();
    }
}
