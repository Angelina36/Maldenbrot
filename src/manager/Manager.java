package manager;

import drawing.Painter;
import listener.Listener;

import javax.swing.*;

<<<<<<< HEAD
    public static int WIDHT = 600;
    public static int HEIGHT = 600;
    public static int MAX_ITER = 200;

=======
public class Manager {
>>>>>>> 0cbf24ecb5af8ee24f4c1dd3277101d8c3e41f21
    public static void main(String[] args){
        Painter painter = new Painter();
        Thread thread = new Thread(painter);
        thread.start();
    }
}
