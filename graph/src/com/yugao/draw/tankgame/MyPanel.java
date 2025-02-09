package com.yugao.draw.tankgame;

import javax.swing.*;
import java.awt.*;

/**
 * @author yugao
 * @version 1.0
 * 游戏绘图区域
 */

public class MyPanel extends JPanel {

    private int panelWidth = 750;
    private int panelHeight = 750;
    Hero hero = null;
    public MyPanel() {
        hero = new Hero(100,100);

    }

    public int getPanelWidth() {
        return panelWidth;
    }

    public void setPanelWidth(int panelWidth) {
        this.panelWidth = panelWidth;
    }

    public int getPanelHeight() {
        return panelHeight;
    }

    public void setPanelHeight(int panelHeight) {
        this.panelHeight = panelHeight;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.fillRect(0, 0, panelWidth, panelHeight);
        drawTank(hero.getX(), hero.getY(), g, 0, 0);
        drawTank(hero.getX() + 100, hero.getY(), g, 0, 1);
    }

    public void drawTank(int x,int y, Graphics g, int direction, int type) {

        switch (type) {
            //己方坦克
            case 0:
                g.setColor(Color.cyan);
                break;
            //敌方坦克
            case 1:
                g.setColor(Color.green);
                break;
        }

        switch (direction) {
            case 0:
                //坦克左边履带
                g.fill3DRect(x, y, 10, 60, false);
                //坦克右边履带
                g.fill3DRect(x + 30, y, 10, 60, false);
                //中间的矩形 身体部分
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                //坦克的盖子
                g.fillOval(x + 10, y + 20, 20, 20);
                //坦克的炮管
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
        }
    }
}
