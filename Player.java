package aaa;

import java.awt.Color;

class Player extends Thread {

    private int playerNumber;
    private int x;
    private int y;
    private Color color;
    boolean moving = true;

    public boolean isPassed=false;


    public Player(int playerNumber, int x, int y, Color color) {
        this.playerNumber = playerNumber;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }


    public void limitPlayerCoordinates() throws InterruptedException {
        int circleCenterX = 370;
        int circleCenterY = 390;

        double distance = Math.sqrt(Math.pow(circleCenterX - x, 2) + Math.pow(circleCenterY - y, 2));

        if (distance > 210 && distance < 340) {


        } else {
            if (distance >= 340 && circleSide() == 2) {
                x += 15;
                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();


            }
            if (distance <= 210 && circleSide() == 2) {
                x -= 15;
                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();


            }
            if (distance >= 340 && circleSide() == 1) {
                y += 15;
                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();


            }
            if (distance <= 210 && circleSide() == 1) {
                y -= 15;
                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();


            }
            if (distance >= 340 && circleSide() == 3) {
                x += 15;
                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();



            }
            if (distance <= 210 && circleSide() == 3) {
                y += 15;
                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();




            }
            if (distance >= 340 && circleSide() == 4) {
                y -= 15;
                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();



            }
            if (distance <= 210 && circleSide() == 4) {
                y += 15;
                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();




            }
        }

    }

    private int circleSide() {

        int side=0;

        if(x<=370 && y<=390){
            side =2;
        }
        if(x>=370 && y<=390){
            side =1;
        }
        if(x<=370 && y>=390){
            side =3;
        }
        if(x>=370 && y>=390){
            side =4;
        }
        return side;
    }


    public void resetPositionPlayer(){

        if(playerNumber==1){
            x=25;
            y=385;
        }

        if(playerNumber==2){
            x=45;
            y=385;
        }
    }

    public void crash(Player other) {
        if(Math.abs(x-other.x)<=8 && Math.abs(y-other.y)<=8){
            x+=20;
            this.moving = false;
            other.moving=false;
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.moving = true;
                other.moving=true;
            }).start();
        }}



    public void crash(Bot bot) {
        if(Math.abs(x-bot.getX())<=8 && Math.abs(y-bot.getY())<=8){
            x+=20;
            this.moving = false;
            bot.moving = false;
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.moving = true;
                bot.moving = true;
            }).start();

        }}

    public boolean isPassedPlayer(){
        if(this.x>370&&this.y>=390){
            isPassed=true;
            return true;
        }
        return false;
    }


    public void run() {
        while (true) {
            isPassedPlayer();
            try {
                limitPlayerCoordinates();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}




