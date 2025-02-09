package com.yugao.draw;

import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JFrame {

    private MyPanel mp = new MyPanel();
    public static void main(String[] args) {

        DrawCircle dc = new DrawCircle();
        dc.drawCircle();

    }

    public void drawCircle(){
        this.add(mp);
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


}

class MyPanel extends JPanel {

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(100, 100, 100, 100);
    }
}
