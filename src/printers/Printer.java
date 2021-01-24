package printers;

import org.jetbrains.annotations.NotNull;
import printers.components.BaseHeater;

public interface Printer {
    enum Models{
        Duet2,
    }
    boolean connect();

    void update();
    @NotNull BaseHeater getHeatedBed();
    @NotNull BaseHeater[] getHeaters();
    @NotNull String getStatus();

    @NotNull String getName();

    float getPercentagePrinted();

    void disconnect();

    void onPrintTerminatedListener(Runnable runnable);
}
