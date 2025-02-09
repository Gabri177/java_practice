package com.yugao.draw.tankgame01;

public class EnamyTank extends Tank implements Runnable {
    public EnamyTank(int x, int y) {
        super(x, y);
    }

    public EnamyTank(int x, int y, int direction) {
        super(x, y, direction);
//        Thread autoBullets = new Thread(()-> {
//            while(true) {
//
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                this.shoot();
//            }
//        });
//        autoBullets.start();
    }

    @Override
    public void moveUp() {
        for (int i = 0; i < 30; i ++){
            super.moveUp();
            if (i % (int)(Math.random() * 4  + 29) == 0){
                shoot();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void moveDown() {
        for (int i = 0; i < 30; i ++){
            super.moveDown();
            if (i % (int)(Math.random() * 4  + 29) == 0){
                shoot();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void moveLeft() {
        for (int i = 0; i < 30; i ++){
            super.moveLeft();
            if (i % (int)(Math.random() * 4  + 29) == 0){
                shoot();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void moveRight() {
        for (int i = 0; i < 30; i ++){
            super.moveRight();
            if (i % (int)(Math.random() * 4  + 29) == 0){
                shoot();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            switch (getDirection()){
                case 0:
                    moveUp();
                    break;
                case 1:
                    moveDown();
                    break;
                case 2:
                    moveLeft();
                    break;
                case 3:
                    moveRight();
                    break;
            }
            setDirection((int)(Math.random() * 4));
            if (!isAlive)
                break;

        }
    }
}
