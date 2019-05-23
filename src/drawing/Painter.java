package drawing;

import manager.Fractal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.*; //buffer image
import javax.swing.*; // for UI components

import static manager.Manager.WINDOW_HEIGHT;
import static manager.Manager.WINDOW_WIDTH;

public class Painter extends JFrame implements Drawable, Runnable {

    @Override
    public void run(){
        setInitialGUIProperties();
        this.addCanvas();
        updateFractal();
        setVisible(true);
    }

    // массив, который получаем откуда-то
    Fractal fractal;

    Canvas canvas; // холст
    BufferedImage fractalImage; //что мы рисуем в canvas


    public Painter(Fractal fractal) {
        this.fractal = fractal;
        this.setVisible(true);
    }

    public void updateFractal() {
            // имеется масси точек откуда-то, итерируем за каждую из них
            for (int i = 0; i < WINDOW_WIDTH; i++) {
                synchronized (fractal.points) {
                    for (int j = 0; j < WINDOW_HEIGHT; j++) {
                    fractalImage.setRGB(fractal.points[i][j].x, fractal.points[i][j].y,
                            fractal.points[i][j].col * fractal.points[i][j].col * fractal.points[i][j].col); // новый цвет для точки

                }
            }
            canvas.repaint();
        }
    }

    //инициализация фрактала и холста
    private void addCanvas() {
        canvas = new Canvas();
        fractalImage = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
        canvas.setVisible(true);
        this.add(canvas, BorderLayout.CENTER); //добавляем холст на фрейм к центру
    }

    public void setInitialGUIProperties() {
        this.setTitle("Fractal Explorer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
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
                setEndPoint(e.getX(), e.getY());
            }

            public void mouseDragged(MouseEvent e) {
                setEndPoint(e.getX(), e.getY());
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                pressed = false;
                setEndPoint(e.getX(), e.getY());

                System.out.println("change to real");
                synchronized (fractal.border) {
                    fractal.border.changerToReal(Math.min(x1, x2), Math.max(x1, x2),
                            Math.min(y1, y2), Math.max(y1, y2));
                }
                updateFractal();
                repaint();

            }
        }

        private Canvas() {
            MyMouseListener listener = new MyMouseListener();
            addMouseListener(listener);
            addMouseMotionListener(listener);

            int delay = 100; //milliseconds
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    updateFractal();
                }
            };
            new Timer(delay, taskPerformer).start();

            x1 = y1 = x2 = y2 = 0; //
            pressed = false;
        }

        // метод, который возвращает размерность холста
        @Override public Dimension getPreferredSize(){
            return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
        }

        // рисует фрактал на холст
        @Override public void paintComponent(Graphics g) {
            g.drawImage(fractalImage, 0, 0, null);
            if (pressed) {
                g.setColor(Color.RED);
                drawPerfectRect(g, x1, y1, x2, y2);
            }
        }
    }

    public void draw() {}

}