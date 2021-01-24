package gui.window;

import org.jetbrains.annotations.NotNull;
import printers.Manager;
import printers.Printer;
import printers.duet.two.DuetTwo;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AddPrinterWindow extends JFrame {

    public AddPrinterWindow(@NotNull Manager manager, DefaultListModel<Printer> listModel) {
        JPanel container = new JPanel(new GridLayout(0,4));
        JComboBox<Printer.Models> modelsJComboBox = new JComboBox<>(Printer.Models.values());
        JTextField nameField = new JTextField("Printer name");
        JTextField ipAddressField = new JTextField("Printer Address (without http(s)://)");
        JButton addButton = new JButton("Add");

        addButton.addActionListener((actionEvent) -> {
            try {
                if (modelsJComboBox.getSelectedItem() != null) {
                    switch ((Printer.Models) modelsJComboBox.getSelectedItem()) {
                        case Duet2 -> manager.addPrinter(new DuetTwo(InetAddress.getByName(ipAddressField.getText()), nameField.getText()));
                    }
                    listModel.addAll(manager.printers);
                    this.dispose();
                }
            } catch (UnknownHostException e) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Invalid printer address!");
            }
        });
        container.add(modelsJComboBox);
        container.add(nameField);
        container.add(ipAddressField);
        container.add(addButton);
        this.add(container);
        this.pack();
        this.setVisible(true);
    }
}
