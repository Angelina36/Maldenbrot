package listener;

public class Mouse { // курсор
    private int x, y;
    private boolean isCleckTrue; // нажата ли левая кнопка мыши

    public int getX() { // меняем и смотрим на приватные поля
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCleckTrue() {
        return isCleckTrue;
    }

    public void setCleckTrue(boolean cleckTrue) {
        isCleckTrue = cleckTrue;
    }
}
