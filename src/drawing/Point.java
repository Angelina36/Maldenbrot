package drawing;

import static manager.Manager.WINDOW_HEIGHT;
import static manager.Manager.WINDOW_WIDTH;

public class Point implements Drawable { // будущие точки
    public int x;
    public int y;
    public int col; // цвет
    public double r;
    public double i;
    public void draw() {

    }

    public Point(int x, int y) { // конструктор точек
        this.x = x;
        this.y = y;
    }

    public void changerToComplex(double borderX1, double borderX2, double borderY1, double borderY2) {
        r = borderX1 + x * (borderX2 - borderX1) / WINDOW_WIDTH;
        i = borderY1 + y * (borderY2 - borderY1) / WINDOW_HEIGHT;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
