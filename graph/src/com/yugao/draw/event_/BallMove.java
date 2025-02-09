package com.yugao.draw.event_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BallMove extends JFrame {

    private Mypanel panel = null;

    public static void main(String[] args) {

        BallMove frame = new BallMove();
    }

    public BallMove() {

        panel = new Mypanel();
        this.add(panel);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(panel);
        this.setVisible(true);
    }
}

class Mypanel extends JPanel implements KeyListener {

    int x = 0, y = 0;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 10, 10);
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                y -= 10;
                break;
            case KeyEvent.VK_DOWN:
                y += 10;
                break;
            case KeyEvent.VK_LEFT:
                x -= 10;
                break;
            case KeyEvent.VK_RIGHT:
                x += 10;
                break;
        }
        repaint(); // 重新绘制
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
