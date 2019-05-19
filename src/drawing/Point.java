package drawing;

public class Point implements Drawable  { // будущие точки
    private int x;
    private int y;
    private int col; // цвет точки
    public void draw() {

    }

    public Point(int x, int y, int col) { // конструктор точек
        this.x = x;
        this.y = y;
        this.col = col;
    }
}
