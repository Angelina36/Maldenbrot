package manager;

public class Border {

    public int SCREEN_SIZE_X = 100;
    public int SCREEN_SIZE_Y = 200;

    private int x1, x2, y1, y2;
    void changer(int xo1, int xo2, int yo1, int yo2) {
        x1 = (x2 - x1) / SCREEN_SIZE_X * xo1 + x1;
        x2 = (x2 - x1) / SCREEN_SIZE_X * xo2 + x1;
        y1 = (y2 - y1) / SCREEN_SIZE_Y * yo1 + y1;
        y2 = (y2 - y1) / SCREEN_SIZE_Y * yo2 + y1;
    }

    public Border(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
}
