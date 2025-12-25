package aaa;


import java.util.Random;

class Bot extends Thread{

    private int botNumber;
    private int x;
    private int y;
    private  int initialX;
    private  int initialY;

    boolean moving = true;
    private int speed;


    public Bot(int botNumber, int x,int y,int speed) {
        this.botNumber = botNumber;
        this.x = x;
        this.y = y;
        this.initialX = x;
        this.initialY = y;
        this.speed=speed;

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBotNumber() {
        return botNumber;
    }

    public void setBotNumber(int botNumber) {
        this.botNumber = botNumber;
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


    private int circleSideBot() {

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


    public boolean limitBotCoordinates() throws InterruptedException {

        int circleCenterX = 370;
        int circleCenterY =390;

        double distance = Math.sqrt(Math.pow(circleCenterX - x, 2) + Math.pow(circleCenterY - y, 2));

        if (distance > 210 && distance < 340) {
            return true;
        }
        else{

            if(distance>=340 && circleSideBot()==2){
                x+=10;
                y+=5;

                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();

                return false;

            }
            if(distance<=210 && circleSideBot()==2){
                x-=15;
                y+=5;

                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();
                return false;

            }
            if(distance>=340 && circleSideBot()==1){
                x-=10;
                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();
                return false;

            }
            if(distance<=210 && circleSideBot()==1){
                x+=10;
                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();
                return false;

            }
            if(distance>=340 && circleSideBot()==3){
                x+=10;
                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();
                return false;

            }
            if(distance<=210 && circleSideBot()==3){
                x-=10;
                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();
                return false;

            }
            if(distance>=340 && circleSideBot()==4){
                x-=10;
                y-=5;
                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();
                return false;

            }
            if(distance<=210 && circleSideBot()==4){
                x+=10;
                y+=5;
                this.moving = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.moving = true;
                }).start();
                return false;

            }

        }
        return true;}


    public void moveClockwise() {
        if(!moving){
            return;
        }
        Random random = new Random();
        int side = circleSideBot();
        int side2d = random.nextInt(2);

        if (side == 2) {
            if(x>20 && x<170 && y<390 && y>365){
                switch (side2d) {
                    case 0:
                        setY(getY() - 1);
                        break;
                    case 1:
                        setY(getY() - 1);
                        break;

                }}

            switch (side2d) {
                case 0:
                    setY(getY() - 1);
                    break;
                case 1:
                    setX(getX() + 1);
                    break;
            }}
        else if (side == 1) {
            switch (side2d) {
                case 0:
                    setY(getY() + 1);
                    break;
                case 1:
                    setX(getX() + 1);
                    break;
            }
        } else if (side == 3) {

            switch (side2d) {
                case 0:
                    setY(getY() - 1);
                    break;
                case 1:
                    setX(getX() - 1);
                    break;
            }
        } else if (side == 4) {
            switch (side2d) {
                case 0:
                    setY(getY() + 1);
                    break;
                case 1:
                    setX(getX() - 1);
                    break;
            }
        }
    }

    public void resetPositionBot(){
        x = initialX;
        y = initialY;
    }


    public void crash(Bot bot) {

        if(Math.abs(x-bot.getX())<=8 && Math.abs(y-bot.getY())<=8){
            x+=20;

            this.moving = false;
            bot.moving=false;
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.moving = true;
                bot.moving=true;
            }).start();
        }}


    public void run() {
        while (true) {
            moveClockwise();
            try {

                limitBotCoordinates();

                Thread.sleep(1000/speed); // Adjust the sleep duration as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
