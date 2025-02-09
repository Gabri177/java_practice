package com.yugao.draw.tankgame01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * @author yugao
 * @version 1.0
 * 游戏绘图区域
 */

public class MyPanel extends JPanel implements KeyListener, Runnable {

    public static int panelWidth = 750;
    public static int panelHeight = 750;

    Vector<Tank> tanks = new Vector<>();
    Vector<Bomb> bombs = new Vector<>();
    Tank heroTank = null;
    ArrayList<Image> bombImages = new ArrayList<>();


    public MyPanel() {
        heroTank = new HeroTank(400, 400);
        tanks.add(heroTank);
        for (int i = 0; i < 3; i++) {
            Tank temp = new EnamyTank(100 * (i + 1), 0, 1);
            new Thread((EnamyTank)temp).start();
            tanks.add(temp);
        }
        bombImages.add(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/bomb_1.gif")));
        bombImages.add(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/bomb_2.gif")));
        bombImages.add(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/bomb_3.gif")));


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, panelWidth, panelHeight);
        for (Tank tank : tanks) {
            drawTank(tank, g);
            if (!tank.getBullets().isEmpty()) {
                for(int i = 0; i < tank.getBullets().size(); i++) {
                    Bullet bullet = tank.getBullets().get(i);
                    if (bullet.isAlive)
                        drawBullet(bullet, g);
//                    else
//                        tank.getBullets().remove(bullet);
                }
            }
        }

        for (Bomb bomb : bombs){
            if (bomb.isLive){
                System.out.println("绘制爆炸... x:" + bomb.getX() + " y: " + bomb.getY());
                if (bomb.getLifeTime() > 6)
                    g.drawImage(bombImages.get(0), bomb.getX(), bomb.getY(), 40, 40, this);
                else if (bomb.getLifeTime() > 3)
                    g.drawImage(bombImages.get(1), bomb.getX(), bomb.getY(), 40, 40, this);
                else
                    g.drawImage(bombImages.get(2), bomb.getX(), bomb.getY(), 40, 40, this);

                bomb.minimizeLifeTime();
                if (bomb.getLifeTime() < 0) {
                    bomb.isLive = false;
                    System.out.println("爆炸结束...");
                }
            }
        }

    }

    private void checkHit(){
        Vector<Bullet> allBullets = new Vector<>();
        for (Tank tank : tanks) {
            allBullets.addAll(tank.getBullets());
        }
        for (Bullet bullet : allBullets) {
            int x = bullet.getx();
            int y = bullet.gety();
            for (int i = 0; i < tanks.size(); i++) {
                Tank tank = tanks.get(i);
                int x1 = tank.getX();
                int y1 = tank.getY();
                if (x > x1 && x < x1 + (Tank.wheelWidth * 2 + Tank.capSize) && y > y1 &&
                    y < y1 + (Tank.wheelHeight)) {
                    System.out.println("子弹击中了 子弹的位置 x:" + x + " y:" + y);
                    System.out.println("\t 坦克的位置: x:" + x1 + " y:" + y1);

                    bombs.add(new Bomb(x1, y1));
                    System.out.println("添加新的爆炸坐标 x:" + x + " y:" + y1);
                    tank.isAlive = false;


                    //tanks.remove(i);
                    bullet.isAlive = false;
                }
            }
        }
    }

    public void drawBullet(Bullet bullet, Graphics g) {
        if (bullet != null) {

            int x = bullet.getx();
            int y = bullet.gety();
            int height = bullet.getHeight();
            int direction = bullet.getDirection();
            g.setColor(Color.white);

            switch (direction) {
                case 0:
                    g.drawLine(x, y, x, y - height);
                    break;
                case 1:
                    g.drawLine(x, y, x, y + height);
                    break;
                case 2:
                    g.drawLine(x, y, x - height, y);
                    break;
                case 3:
                    g.drawLine(x, y, x + height, y);
            }
        }
    }

    public void drawTank(Tank tank, Graphics g) {

        int x = tank.getX();
        int y = tank.getY();
        int direction = tank.getDirection();
        if (tank instanceof HeroTank) {
            g.setColor(Color.cyan);
        } else if (tank instanceof EnamyTank) {
            g.setColor(Color.green);
        }


        switch (direction) {

            //方向朝上
            case 0:
                //坦克左边履带
                g.fill3DRect(x, y, Tank.wheelWidth, Tank.wheelHeight, false);
                //坦克右边履带
                g.fill3DRect(x + (Tank.wheelWidth + Tank.roomWidth), y, Tank.wheelWidth, Tank.wheelHeight, false);
                //中间的矩形 身体部分
                g.fill3DRect(x + Tank.wheelWidth, y + ((Tank.wheelHeight - Tank.roomHeight) / 2), Tank.roomWidth, Tank.roomHeight, false);
                //坦克的盖子
                g.fillOval(x + Tank.wheelWidth, y + ((Tank.wheelHeight - Tank.capSize) / 2), Tank.capSize, Tank.capSize);
                //坦克的炮管
                g.drawLine(x + (Tank.wheelWidth + Tank.capSize / 2), y + (Tank.wheelHeight / 2), x + (Tank.wheelWidth + Tank.capSize / 2), y);
                break;

            //方向朝下
            case 1:
                g.fill3DRect(x, y, Tank.wheelWidth, Tank.wheelHeight, false);
                g.fill3DRect(x + (Tank.wheelWidth + Tank.roomWidth), y, Tank.wheelWidth, Tank.wheelHeight, false);
                g.fill3DRect(x + Tank.wheelWidth, y + ((Tank.wheelHeight - Tank.roomHeight) / 2), Tank.roomWidth, Tank.roomHeight, false);
                g.fillOval(x + Tank.wheelWidth, y + ((Tank.wheelHeight - Tank.capSize) / 2), Tank.capSize, Tank.capSize);
                g.drawLine(x + (Tank.wheelWidth + Tank.capSize / 2), y + 2 * (Tank.wheelHeight / 2), x + (Tank.wheelWidth + Tank.capSize / 2), y + (Tank.wheelHeight / 2));
                break;
            //方向朝向左
            case 2:
                g.fill3DRect(x, y, Tank.wheelHeight, Tank.wheelWidth, false);
                g.fill3DRect(x, y + (Tank.wheelWidth + Tank.roomWidth), Tank.wheelHeight, Tank.wheelWidth, false);
                g.fill3DRect(x + ((Tank.wheelHeight - Tank.roomHeight) / 2), y + Tank.wheelWidth, Tank.roomHeight, Tank.roomWidth, false);
                g.fillOval(x + ((Tank.wheelHeight - Tank.capSize) / 2), y + Tank.wheelWidth, Tank.capSize, Tank.capSize);
                g.drawLine(x + (Tank.wheelHeight / 2), y + (Tank.wheelWidth + Tank.capSize / 2), x, y + (Tank.wheelWidth + Tank.capSize / 2));
                break;
            //方向朝向右
            case 3:
                g.fill3DRect(x, y, Tank.wheelHeight, Tank.wheelWidth, false);
                g.fill3DRect(x, y + (Tank.wheelWidth + Tank.roomWidth), Tank.wheelHeight, Tank.wheelWidth, false);
                g.fill3DRect(x + ((Tank.wheelHeight - Tank.roomHeight) / 2), y + Tank.wheelWidth, Tank.roomHeight, Tank.roomWidth, false);
                g.fillOval(x + ((Tank.wheelHeight - Tank.capSize) / 2), y + Tank.wheelWidth, Tank.capSize, Tank.capSize);
                g.drawLine(x + 2 * (Tank.wheelHeight / 2), y + (Tank.wheelWidth + Tank.capSize / 2), x + (Tank.wheelHeight / 2), y + (Tank.wheelWidth + Tank.capSize / 2));
                break;


        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                heroTank.setDirection(0);
                heroTank.moveUp();
                break;
            case KeyEvent.VK_S:
                heroTank.setDirection(1);
                heroTank.moveDown();
                break;
            case KeyEvent.VK_A:
                heroTank.setDirection(2);
                heroTank.moveLeft();
                break;
            case KeyEvent.VK_D:
                heroTank.setDirection(3);
                heroTank.moveRight();
                break;
            case KeyEvent.VK_J:
                heroTank.shoot();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void cleanGarbage(){

        for (int i = 0; i < tanks.size(); i++){
            Tank current = tanks.get(i);
            if (!current.isAlive){
                tanks.remove(i);
            } else {
                for (int j = 0; j < current.getBullets().size(); j++){
                    Bullet bullet = current.getBullets().get(j);
                    if (!bullet.isAlive)
                        current.getBullets().remove(j);
                }
            }
        }

        for (int i = 0; i < bombs.size(); i++){
            Bomb bomb = bombs.get(i);
            if (!bomb.isLive) {
                bombs.remove(i);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            checkHit();
            repaint();
            cleanGarbage();
        }

    }
}
