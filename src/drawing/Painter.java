package drawing;

import java.awt.*;
import java.awt.image.*; //buffer image
import javax.swing.*; // for UI components

public class Painter extends JFrame implements Drawable, Runnable{


    @Override
    public void run(){
        Painter painter = new Painter();
    }

    /*public void cleaner(){

    }*/

    static final int WIDTH = 600;
    static final int HEIGHT = 600;
    static final int SIZE_ARRAY = WIDTH*HEIGHT;

    // массив, который получаем откуда-то
    Point[] points;

    Canvas canvas; //холст
    BufferedImage fractalImage; //что мы рисуем в canvas


    public Painter() {
        setInitialGUIProperties(); //
        addCanvas(); //инициализация фрактала и холста
        updateFractal(); // обнавляет рисунку при изменении мастштаба


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
    public void addCanvas() {
        canvas = new Canvas();
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

        // метод, который возвращает размерность холста
        @Override public Dimension getPreferredSize(){
            return new Dimension(WIDTH, HEIGHT);
        }

        // рисует фрактал на холст
        @Override public void paintComponent(Graphics drawingObj) {
            drawingObj.drawImage(fractalImage, 0, 0, null);
        }
    }

    public void draw() {}

}