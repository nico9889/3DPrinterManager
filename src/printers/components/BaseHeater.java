package printers.components;

import org.jetbrains.annotations.NotNull;

public abstract class BaseHeater {
    public final String name;
    public final int number;
    protected float targetTemperature = 0;
    protected float temperature = 0;
    protected float standbyTemperature = 0;

    public BaseHeater(@NotNull String name, int number){
        this.name = name;
        this.number = number;
    }

    public void update(float targetTemperature, float temperature, float standbyTemperature){
        this.targetTemperature = targetTemperature;
        this.temperature = temperature;
        this.standbyTemperature = standbyTemperature;
    }

    public abstract float getTargetTemperature();

    public abstract void setTargetTemperature(float targetTemperature);

    public abstract float getTemperature();

    public abstract float getStandbyTempeature();
}
