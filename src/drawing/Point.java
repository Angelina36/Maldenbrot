package drawing;

import static manager.Manager.HEIGHT;
import static manager.Manager.WIDHT;

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

    public void changerToComplex (double borderX1, double borderX2, double borderY1, double borderY2) {
        r = borderX1 + x * (borderX2 - borderX1) / WIDHT;
        i = borderY1 - y * (borderY1 - borderY2) / HEIGHT;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
