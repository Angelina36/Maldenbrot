package manager;

import drawing.Point;

import static manager.Manager.*;

public class Fractal implements Runnable {
    @Override
    public void run(){

    }

    Point[][] points;

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

    public void step(){
        for (int i = 0; i < WIDHT; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                points[i][j] = new Point(i, j);
                points[i][j].changerToComplex();
                points[i][j].setCol(this.computeIterations(points[i][j].r, points[i][j].i));

            }
        }

    }
}
