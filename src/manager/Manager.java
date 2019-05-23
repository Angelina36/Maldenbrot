package manager;

import drawing.Painter;
import listener.Listener;

public class Manager {

    public static int WIDHT = 600;
    public static int HEIGHT = 600;
    public static int MAX_ITER = 200;

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Listener());
        javax.swing.SwingUtilities.invokeLater(new Painter());
    }
}
