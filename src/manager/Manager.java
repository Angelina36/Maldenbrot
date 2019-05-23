package manager;

import drawing.Painter;
import listener.Listener;

import javax.swing.*;

public class Manager {
    public static void main(String[] args){
        Painter painter = new Painter();
        Thread thread = new Thread(painter);
        thread.start();
    }
}
