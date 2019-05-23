package drawing;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.*; //buffer image
import javax.swing.*; // for UI components

public class Painter extends JFrame implements Drawable, Runnable {

    @Override
    public void run(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Rectangle rectangle = new Rectangle();
        this.addCanvas(rectangle);
        setSize(300, 300);
        setVisible(true);
    }

    static final int WIDTH = 600;
    static final int HEIGHT = 600;
    static final int SIZE_ARRAY = WIDTH*HEIGHT;

    // массив, который получаем откуда-то
    Point[] points;

    Canvas canvas; //холст
    BufferedImage fractalImage; //что мы рисуем в canvas


    public Painter() {

        this.setVisible(true);
    }

    public void updateFractal() {

        // имеется масси точек откуда-то, итерируем за каждую из них
        for (int i = 0; i < SIZE_ARRAY; i++) {
            fractalImage.setRGB(points[i].x, points[i].y, points[i].col); // новый цвет для точки
        }

        canvas.repaint();
    }

    //инициализация фрактала и холста
    private void addCanvas(Rectangle rectangle) {
        canvas = new Canvas(rectangle);
        fractalImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        canvas.setVisible(true);
        this.add(canvas, BorderLayout.CENTER); //добавляем холст на фрейм к центру
    }

    public void setInitialGUIProperties() {
        this.setTitle("Fractal Explorer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }


    // Рисование холста
    private class Canvas extends JPanel {

        int x1, y1, x2, y2;
        boolean pressed;

        private void setStartPoint(int x, int y) {
            x1 = x;
            y1 = y;
        }

        private void setEndPoint(int x, int y) {
            x2 = x;
            y2 = y;
        }

        private void drawPerfectRect(Graphics g, int x1, int y1, int x2, int y2) {
            int x_final = Math.min(x1, x2);
            int y_final = Math.min(y1, y2);
            int width = Math.abs(x1 - x2);
            int height = Math.abs(y1 - y2);

            // method from Graphics
            g.drawRect(x_final, y_final, width, height);
        }

        class MyMouseListener extends MouseAdapter {

            public void mousePressed(MouseEvent e) {
                pressed = true;
                setStartPoint(e.getX(), e.getY());
            }

            public void mouseDragged(MouseEvent e) {
                setEndPoint(e.getX(), e.getY());
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                pressed = false;
                setEndPoint(e.getX(), e.getY());
                repaint();

            }
        }

        private Canvas(Rectangle rectangle) {
            MyMouseListener listener = new MyMouseListener();
            addMouseListener(listener);
            addMouseMotionListener(listener);
            x1 = y1 = x2 = y2 = 0; //
            pressed = false;
        }

        // метод, который возвращает размерность холста
        @Override public Dimension getPreferredSize(){
            return new Dimension(WIDTH, HEIGHT);
        }

        // рисует фрактал на холст
        @Override public void paintComponent(Graphics g) {
            g.drawImage(fractalImage, 0, 0, null);
            if (pressed) {
                System.out.println(222);
                g.setColor(Color.RED);
                drawPerfectRect(g, x1, y1, x2, y2);
            }
            g.setColor(Color.blue);
            g.drawRect(30, 30, 100, 100);
        }
    }

    public void draw() {}

}