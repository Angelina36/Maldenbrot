package manager;

import drawing.Point;

import static manager.Manager.*;

public class Fractal implements Runnable {

    public final Border border = new Border(-2, 2, 2, -2);
    boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        for (;;) {
            synchronized (border) {
                if (border.isNew) {
                    System.out.println("@@@@");
                    flag = true;
                    border.isNew = false;
                }
            }
            if (flag) {
                step();
                flag = false;
            }
            i += 1;
        }
    }

    public Fractal() {
        points = new Point[WINDOW_WIDTH][WINDOW_HEIGHT];
        for (int i = 0; i < WINDOW_WIDTH; i++) {
            points[i] = new Point[WINDOW_HEIGHT];
        }
        for (int i = 0; i < WINDOW_WIDTH; i++) {
            for (int j = 0; j < WINDOW_HEIGHT; j++) {
                points[i][j] = new Point(i, j);
            }
        }
    }

    void setBorder(int xo1, int xo2, int yo1, int yo2) {
        this.border.changerToReal(xo1, xo2, yo1, yo2);
    }

    public Point[][] points;

    private int computeIterations(double c_r, double c_i) {

    /*

    Let c = c_r + c_i
    Let z = z_r + z_i
    z' = z*z + c
       = (z_r + z_i)*(z_r + z_i) + c_r + c_i
       = z_r^2 + 2*z_r*z_i - z_i^2 + c_r + c_i
       = z_r' + z_i'

       where z_r' = z_r^2  - z_i^2 + c_r
             z_i' = 2*z_r*z_i + c_i
    */

        // the initial values of z (z_0 = 0)

        double z_r = 0.0;
        double z_i = 0.0;

        int iterCount = 0;

        //calculate distance to origin: \sqrt(a^2 + b^2) <= 2.0
        // --> a^2 + b^2  <= 4.0

        while (z_r * z_r + z_i * z_i <= 4.0) {

            double z_r_temp = z_r;

            //z_r' = z_r^2  - z_i^2 + c_r
            z_r = z_r * z_r - z_i * z_i  + c_r;
            z_i = 2 * z_i * z_r_temp + c_i;

            //checking if point is in the maldebrot set

            if (iterCount >= MAX_ITER) {
                return MAX_ITER;
            }

            iterCount++;
        }

        // Complex point was outside
        return iterCount;

    }

    public void step() {
        System.out.println(border.x1 + " " + border.x2);
        for (int i = 0; i < WINDOW_WIDTH; i++) {
            synchronized (points) {
                for (int j = 0; j < WINDOW_HEIGHT; j++) {
                    points[i][j] = new Point(i, j);
                    points[i][j].changerToComplex(border.x1, border.x2, border.y1, border.y2);
                    points[i][j].setCol(this.computeIterations(points[i][j].r, points[i][j].i));
                }
            }
        }

    }
}
