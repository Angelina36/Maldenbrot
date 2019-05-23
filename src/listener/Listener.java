package listener;

import java.awt.*;
import java.util.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Listener extends JFrame implements Runnable {
    private JPanel mousepanel; // генерим канвас
    private JLabel statusbar; // генерим панельку в канвасе

    @Override
    public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setPreferredSize(new Dimension(330, 160));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public Listener() { // делаем конструктор
        super("title"); // в JFrame уже есть конструктор, так что мы унаследовали его и сразу поменяли заголовок.

        mousepanel = new JPanel();
        mousepanel.setBackground(Color.WHITE);
        add(mousepanel, BorderLayout.CENTER); // по типу метода setLocationRelativeTo(null), пихаем рамку в центр

        statusbar = new JLabel(); // в скобках можно написать текст, который будет при открытии канваса в панели
        add(statusbar, BorderLayout.SOUTH);  // панелька в самом низу

        Actions act = new Actions(); // делаем объект act класса Actions
        //mousepanel.addMouseListener(act);
        mousepanel.addMouseMotionListener(act);


    }

    private class Actions implements  MouseListener,  MouseMotionListener { // 5 методов в первом интерфейсе и 2 во втором , методы не абстрактные, еслит выкинуть, то будет ругаться
        int coorx;
        int coory;

        int pressX;
        int pressY;

        int releaseX;
        int releaseY;

        boolean press;


        public void mouseClicked(MouseEvent event) {
            statusbar.setText(String.format("Clicked at %d, %d", event.getX(), event.getY()));
        }

        public void mousePressed(MouseEvent event) {
            statusbar.setText("You pressed down the mouse");
            press = true;
            pressX = event.getX();
            pressY = event.getY();
            System.out.printf("нажали %d, %d%n", pressX, pressY);
        }

        public void mouseReleased(MouseEvent event) { // отпутили мышку
            statusbar.setText("You released the button");
            releaseX = event.getX();
            releaseY = event.getY();
            press = false;
            System.out.printf("отпустили %d, %d%n", releaseX, releaseY);
        }

        public void mouseEntered(MouseEvent event) {
            // statusbar.setText("You entered the area");
            mousepanel.setBackground(Color.RED);
        }

        public void mouseExited(MouseEvent event) {
            //  statusbar.setText("the mouse had left the window");
            mousepanel.setBackground(Color.WHITE);
        }



        // пошли mouse motion
        public void mouseDragged(MouseEvent event) { // нажали и дергаем
            statusbar.setText("you are dragging the mouse");
            press = true;
            //pressX = event.getX();  выводит текущие координаты при движении зажатой мышки
            //pressY = event.getY();
            //System.out.printf("%d, %d%n", pressX, pressY);
        }

        public void mouseMoved(MouseEvent event) { // просто двигаем
            statusbar.setText("you are moving the mouse");
            statusbar.setText(String.format("Moved at %d, %d", event.getX(), event.getY())); // методы по всей видимости возвращают координаты
            coorx = event.getX();
            coory = event.getY();
            //System.out.printf("%d, %d%n", coorx, coory);
        }
    }
}


/*public class MouseEvents {
    public static void main(String[] args) {
        Event go = new Event(); // создаем объект go
        go.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        go.setSize(300,300);
        go.setVisible(true);
    }

}*/

