package manager;

import static manager.Manager.WIDHT;
import static manager.Manager.HEIGHT;

public class Border {
    private int x1, x2, y1, y2;
    void changerToReal(int xo1, int xo2, int yo1, int yo2) {
        int xOld1 = x1;
        int xOld2 = x2;
        int yOld1 = y1;
        int yOld2 = y2;

        x1 = (xOld2 - xOld1) / WIDHT * xo1 + xOld1;
        x2 = xOld2 - (xOld2 - xOld1) / WIDHT * (WIDHT - xo2);
        y1 = yOld1 - (yOld1 - yOld2) / HEIGHT * (HEIGHT - yo1);
        y2 = (yOld1 - yOld2) / HEIGHT* yo2 + yOld1;
    }

    public Border(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
}
