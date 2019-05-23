package drawing;

public class Point implements Drawable { // будущие точки
    public int x;
    public int y;
    public int col; // цвет точки
    public void draw() {

    }

    public Point(int x, int y, int col) { // конструктор точек
        this.x = x;
        this.y = y;
        this.col = col;
    }
}
