package com.yugao.draw.tankgame01;

public class Bullet implements Runnable {
    private int height;
    private int x;
    private int y;
    private int speed;
    private int direction;
    private Tank tank;
    public boolean isAlive = false;

    {
        speed = 5;
        height = 3;
    }
    
    public Bullet(int x, int y, int direction, Tank tank) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tank = tank;
    }

    @Override
    public void run() {
        isAlive = true;
        while (x > 0 && x < MyPanel.panelWidth && y > 0 && y < MyPanel.panelHeight) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            move();
            //System.out.println("x:" + x + " y:" + y + " direction:" + direction);
        }
        isAlive = false;
        tank.removeBullet(this);

    }

    public void move(){
        switch(direction){
            case 0:
                y -= speed;
                break;
            case 1:
                y += speed;
                break;
            case 2:
                x -= speed;
                break;
            case 3:
                x += speed;
                break;
        }
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getx() {
        return x;
    }

    public void setx(int x) {
        this.x = x;
    }

    public int gety() {
        return y;
    }

    public void sety(int y) {
        this.y = y;
    }
}
