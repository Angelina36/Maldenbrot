package drawing;
import javax.swing.*;

import java.awt.*; //for math

import java.awt.event.*; //for mouse event

public class Rectangle extends JPanel {

    int x1, y1, x2, y2;

    // create frame
    /*public static void main(String[] args) {
        JFrame f = new JFrame("Fractal Explorer");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(new Rectangle());
        f.setSize(300, 300);
        f.setVisible(true);
    }*/


    // call each time we call the method repaint()
    Rectangle() {
        x1 = y1 = x2 = y2 = 0; //
        MyMouseListener listener = new MyMouseListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    public void setStartPoint(int x, int y) {
        x1 = x;
        y1 = y;
    }

    public void setEndPoint(int x, int y) {
        x2 = x;
        y2 = y;
    }

    public void drawPerfectRect(Graphics g, int x1, int y1, int x2, int y2) {
        int x_final = Math.min(x1,x2);
        int y_final = Math.min(y1,y2);
        int width =Math.abs(x1-x2);
        int height =Math.abs(y1-y2);

        // method from Graphics
        g.drawRect(x_final, y_final, width, height);
    }


    class MyMouseListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            setStartPoint(e.getX(), e.getY());
        }

        public void mouseDragged(MouseEvent e) {
            setEndPoint(e.getX(), e.getY());
            repaint();
        }

        public void mouseReleased(MouseEvent e) {
            setEndPoint(e.getX(), e.getY());
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        drawPerfectRect(g, x1, y1, x2, y2);
    }

}