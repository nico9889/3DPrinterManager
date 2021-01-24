package printers.duet;

import org.jetbrains.annotations.NotNull;
import printers.Printer;

import java.net.InetAddress;

public abstract class Duet implements Printer {
    protected final InetAddress address;
    public Duet(@NotNull InetAddress address){
        this.address = address;
    }
}
