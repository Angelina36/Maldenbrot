package manager;

import static manager.Manager.WINDOW_WIDTH;
import static manager.Manager.WINDOW_HEIGHT;

public class Border {

    double x1, x2, y1, y2;
    public boolean isNew = true;
    public void changerToReal(int xo1, int xo2, int yo1, int yo2) {
        double xOld1 = x1;
        double xOld2 = x2;
        double yOld1 = y1;
        double yOld2 = y2;
        isNew = true;
        System.out.println("!!!");

        //int d = Math.max((xo2 - xo1), (yo2 - yo1));
        //xo2 = xo1 + d;
        //yo2 = yo1 + d;

        x1 = (xOld2 - xOld1) / WINDOW_WIDTH * xo1 + xOld1;
        x2 = xOld2 - (xOld2 - xOld1) / WINDOW_WIDTH * (WINDOW_WIDTH - xo2);
        y2 = yOld2 - (yOld2 - yOld1) / WINDOW_HEIGHT * (WINDOW_HEIGHT - yo2);
        y1 = (yOld2 - yOld1) / WINDOW_HEIGHT * yo1 + yOld1;


    }

    Border(double x1, double x2, double y1, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
}
