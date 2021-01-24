import gui.Window;
import printers.Manager;
import printers.Printer;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            ArrayList<Printer> printers = new ArrayList<>();
            Manager manager = new Manager(printers);
            Window window = new Window(manager);
            while(true){
                window.update();
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
