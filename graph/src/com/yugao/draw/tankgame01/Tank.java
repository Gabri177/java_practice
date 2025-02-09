package com.yugao.draw.tankgame01;

import java.util.Vector;

public class Tank {
    private int x = 0;
    private int y = 0;
    private int direction = 0;
    private int speed = 5;
    private Vector<Bullet> bullets = new Vector<>();
    private Bullet bullet = null;
    public  boolean isAlive = true;

    public static final int wheelWidth = 10;
    public static final int wheelHeight = 40;
    public static final int capSize = 20;
    public static final int roomWidth = 20;
    public static final int roomHeight = 30;


    public void shoot() {
        bullet = createBullet();
        bullets.add(bullet);
        Thread thread = new Thread(bullet);
        thread.start();
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    private Bullet createBullet() {

        return switch (direction) {
            case 0 -> new Bullet(x + (wheelWidth * 2 + capSize) / 2, y, direction, this);
            case 1 -> new Bullet(x + (wheelWidth * 2 + capSize) / 2, y + wheelHeight, direction, this);
            case 2 -> new Bullet(x, y + (wheelHeight) / 2, direction, this);
            case 3 -> new Bullet(x + (wheelWidth * 2 + capSize), y + (wheelHeight) / 2, direction, this);
            default -> null;
        };
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tank(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
    }

    public void moveUp(){
        if (this.y - speed < 0)
            this.y = 0;
        else
            this.y -= speed;

    }

    public void moveDown(){
        if (this.y + speed > MyPanel.panelHeight - wheelHeight)
            this.y = MyPanel.panelHeight - wheelHeight;
        else
            this.y += speed;
    }

    public void moveLeft(){
        if (this.x - speed < 0)
            this.x = 0;
        else
            this.x -= speed;
    }

    public void moveRight(){
        if (this.x + speed > MyPanel.panelWidth - (wheelWidth * 2 + capSize))
            this.x = MyPanel.panelWidth - (wheelWidth * 2 + capSize);
        else
            this.x += speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
