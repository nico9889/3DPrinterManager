package gui.panels;

import org.jetbrains.annotations.NotNull;
import printers.components.BaseHeater;

import javax.swing.*;
import java.awt.*;

public class HeaterDataPanel extends JPanel {
    private final BaseHeater heater;
    private final JLabel nameLabel;
    private final JLabel numberLabel;
    private final JLabel currentTemperatureValueLabel;
    private final JLabel targetTemperatureValueLabel;
    private final JLabel standbyTemperatureValueLabel;

    public HeaterDataPanel(@NotNull BaseHeater heater){
        super(new GridLayout(0,2));
        this.heater = heater;

        nameLabel = new JLabel(heater.name);
        numberLabel = new JLabel(String.valueOf(heater.number));

        JPanel namePanel = new JPanel(new GridLayout(0,2));
        namePanel.add(nameLabel);
        namePanel.add(numberLabel);

        currentTemperatureValueLabel = new JLabel(String.valueOf(heater.getTemperature()));
        targetTemperatureValueLabel = new JLabel(String.valueOf(heater.getTemperature()));
        standbyTemperatureValueLabel = new JLabel(String.valueOf(heater.getStandbyTempeature()));

        JPanel dataPanel = new JPanel(new GridLayout(0,3));
        dataPanel.add(currentTemperatureValueLabel);
        dataPanel.add(targetTemperatureValueLabel);
        dataPanel.add(standbyTemperatureValueLabel);

        this.add(namePanel);
        this.add(dataPanel);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public void update() {
        if(heater != null) {
            nameLabel.setText(heater.name);
            numberLabel.setText(String.valueOf(heater.number));
            currentTemperatureValueLabel.setText(String.valueOf(heater.getTemperature()));
            targetTemperatureValueLabel.setText(String.valueOf(heater.getTargetTemperature()));
            standbyTemperatureValueLabel.setText(String.valueOf(heater.getStandbyTempeature()));
        }
    }
}
