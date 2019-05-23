package manager;

import drawing.Painter;
import listener.Listener;

public class Manager {

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Listener());
        javax.swing.SwingUtilities.invokeLater(new Painter());
    }
}
