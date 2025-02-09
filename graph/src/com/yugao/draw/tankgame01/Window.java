package com.yugao.draw.tankgame01;

import javax.swing.*;

/**
 * @author yugao
 * @version 1.0
 */

public class Window extends JFrame {

    private MyPanel panel = null;
    public static void main(String[] args) {
        new Window();
    }

    public Window(){
        panel = new MyPanel();
        Thread thread = new Thread(panel);
        thread.start();
        this.add(panel);
//        this.setTitle("Tank Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(MyPanel.panelWidth, MyPanel.panelHeight);
        this.addKeyListener(panel);
        this.setVisible(true);
    }

}
