package com.yugao.draw.tankgame01;

public class Bomb {
    private int x;
    private int y;
    private int lifeTime = 10;
    public boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void minimizeLifeTime() {
        lifeTime--;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
