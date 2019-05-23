package manager;

import drawing.Painter;

public class Manager {
    public static int WINDOW_WIDTH = 600;
    public static int WINDOW_HEIGHT = 600;
    public static int MAX_ITER = 200;

    public static void main(String[] args){
        Fractal fractal = new Fractal();
        Thread threadFractal = new Thread(fractal);
        threadFractal.start();

        Painter threadPainter = new Painter(fractal);
        Thread thread = new Thread(threadPainter);
        thread.start();
    }
}
