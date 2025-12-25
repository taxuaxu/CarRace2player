package aaa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CarRace extends JFrame {

    private long startTime;
    private JLabel timerLabel;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;
    private final Color TRACK_COLOR = Color.BLACK;

    private boolean game = true;

    private Player[] players = new Player[2];
    private Bot[] bots = new Bot[5];

    private JPanel gamePanel;
    private HashMap<Integer, Boolean> keysPressed;
    private int speed=5;


    Player player1 = new Player(1, 20, 385, Color.GREEN);
    Player player2 = new Player(2, 50, 385, Color.RED);

    Bot bot1 = new Bot(1, 65, 385,speed);
    Bot bot2 = new Bot(2, 85, 385,speed);
    Bot bot3 = new Bot(3, 105, 385,speed);
    Bot bot4 = new Bot(4, 125, 385,speed);
    Bot bot5 = new Bot(5, 145, 385,speed);


    public CarRace() {
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);


        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTrack(g);
                drawPlayers(g);
                drawBots(g);
            }
        };
        gamePanel.setBackground(Color.WHITE);
        gamePanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.add(gamePanel);

        timerLabel = new JLabel("00:00:00");
        timerLabel.setBounds(10, 10, 100, 20);

        gamePanel.add(timerLabel);
        startTime = System.currentTimeMillis();

        players[0] = player1;
        players[1] = player2;



        bots[0] = bot1;
        bots[1] = bot2;
        bots[2] = bot3;
        bots[3] = bot4;
        bots[4] = bot5;

        setupKeyListeners();

        keysPressed = new HashMap<>();
        startGameLoop();
        this.setVisible(true);

    }


    private void drawTrack(Graphics g) {
        g.setColor(TRACK_COLOR);
        g.drawOval(20, 40, 700, 700);
        g.drawOval(170, 190, 400, 400);
        g.drawLine(20, 390, 170, 390);
        g.drawOval(370, 390, 1, 1);

    }

    private void drawPlayers(Graphics g) {
        for (Player player : players) {
            g.setColor(player.getColor());
            g.fillRect(player.getX(), player.getY(), 10, 10);
        }
    }

    private void drawBots(Graphics g) {
        g.setColor(Color.BLACK);
        for (Bot bot : bots) {
            g.fillRect(bot.getX(), bot.getY(), 10, 10);
        }
    }

    private void setupKeyListeners() {
        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keysPressed.put(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keysPressed.put(e.getKeyCode(), false);
            }
        });
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
    }


    private void startGameLoop() {
        Thread gameThread = new Thread(this::run);
        gameThread.start();
    }

    private void handlePlayerMovements() {
        if(!players[0].moving&&!players[1].moving){
            return;
        }
        if(!players[0].moving){

            if (keysPressed.getOrDefault(KeyEvent.VK_UP, false )&& keysPressed.getOrDefault(KeyEvent.VK_LEFT, false)){
                players[1].setY(players[1].getY() + 1);
                players[1].setX(players[1].getX() + 1);

            }

            if (keysPressed.getOrDefault(KeyEvent.VK_UP, false )&& keysPressed.getOrDefault(KeyEvent.VK_RIGHT, false)){
                players[1].setY(players[1].getY() + 1);
                players[1].setX(players[1].getX() - 1);

            }

            if (keysPressed.getOrDefault(KeyEvent.VK_DOWN, false )&& keysPressed.getOrDefault(KeyEvent.VK_RIGHT, false)){

                players[1].setY(players[1].getY() - 1);
                players[1].setX(players[1].getX() - 1);

            }

            if (keysPressed.getOrDefault(KeyEvent.VK_DOWN, false )&& keysPressed.getOrDefault(KeyEvent.VK_LEFT, false)){

                players[1].setY(players[1].getY() - 1);
                players[1].setX(players[1].getX() + 1);

            }
            if (keysPressed.getOrDefault(KeyEvent.VK_UP, false)) {
                players[1].setY(players[1].getY() - 2);
            }
            if (keysPressed.getOrDefault(KeyEvent.VK_DOWN, false)) {
                players[1].setY(players[1].getY() + 2);
            }
            if (keysPressed.getOrDefault(KeyEvent.VK_LEFT, false)) {
                players[1].setX(players[1].getX() - 2);
            }
            if (keysPressed.getOrDefault(KeyEvent.VK_RIGHT, false)) {
                players[1].setX(players[1].getX() + 2);
            }
            return;
        }
        if(!players[1].moving){

            if (keysPressed.getOrDefault(KeyEvent.VK_W, false )&& keysPressed.getOrDefault(KeyEvent.VK_A, false)){
                players[0].setY(players[0].getY() + 1);
                players[0].setX(players[0].getX() + 1);

            }

            if (keysPressed.getOrDefault(KeyEvent.VK_W, false )&& keysPressed.getOrDefault(KeyEvent.VK_D, false)){
                players[0].setY(players[0].getY() + 1);
                players[0].setX(players[0].getX() - 1);

            }

            if (keysPressed.getOrDefault(KeyEvent.VK_S, false )&& keysPressed.getOrDefault(KeyEvent.VK_D, false)){

                players[0].setY(players[0].getY() - 1);
                players[0].setX(players[0].getX() - 1);

            }

            if (keysPressed.getOrDefault(KeyEvent.VK_S, false )&& keysPressed.getOrDefault(KeyEvent.VK_A, false)){

                players[0].setY(players[0].getY() - 1);
                players[0].setX(players[0].getX() + 1);

            }

            if (keysPressed.getOrDefault(KeyEvent.VK_W, false)) {
                players[0].setY(players[0].getY() - 2);
            }
            if (keysPressed.getOrDefault(KeyEvent.VK_S, false)) {
                players[0].setY(players[0].getY() + 2);
            }
            if (keysPressed.getOrDefault(KeyEvent.VK_A, false)) {
                players[0].setX(players[0].getX() - 2);
            }
            if (keysPressed.getOrDefault(KeyEvent.VK_D, false)) {
                players[0].setX(players[0].getX() + 2);
            }
         return;

        }
        if (keysPressed.getOrDefault(KeyEvent.VK_UP, false )&& keysPressed.getOrDefault(KeyEvent.VK_LEFT, false)){
            players[1].setY(players[1].getY() + 1);
            players[1].setX(players[1].getX() + 1);

        }

        if (keysPressed.getOrDefault(KeyEvent.VK_UP, false )&& keysPressed.getOrDefault(KeyEvent.VK_RIGHT, false)){
            players[1].setY(players[1].getY() + 1);
            players[1].setX(players[1].getX() - 1);

        }

        if (keysPressed.getOrDefault(KeyEvent.VK_DOWN, false )&& keysPressed.getOrDefault(KeyEvent.VK_RIGHT, false)){

            players[1].setY(players[1].getY() - 1);
            players[1].setX(players[1].getX() - 1);

        }

        if (keysPressed.getOrDefault(KeyEvent.VK_DOWN, false )&& keysPressed.getOrDefault(KeyEvent.VK_LEFT, false)){

            players[1].setY(players[1].getY() - 1);
            players[1].setX(players[1].getX() + 1);

        }



        if (keysPressed.getOrDefault(KeyEvent.VK_W, false )&& keysPressed.getOrDefault(KeyEvent.VK_A, false)){
            players[0].setY(players[0].getY() + 1);
            players[0].setX(players[0].getX() + 1);

        }

        if (keysPressed.getOrDefault(KeyEvent.VK_W, false )&& keysPressed.getOrDefault(KeyEvent.VK_D, false)){
            players[0].setY(players[0].getY() + 1);
            players[0].setX(players[0].getX() - 1);

        }

        if (keysPressed.getOrDefault(KeyEvent.VK_S, false )&& keysPressed.getOrDefault(KeyEvent.VK_D, false)){

            players[0].setY(players[0].getY() - 1);
            players[0].setX(players[0].getX() - 1);

        }

        if (keysPressed.getOrDefault(KeyEvent.VK_S, false )&& keysPressed.getOrDefault(KeyEvent.VK_A, false)){

            players[0].setY(players[0].getY() - 1);
            players[0].setX(players[0].getX() + 1);

        }




        if (keysPressed.getOrDefault(KeyEvent.VK_UP, false)) {
            players[1].setY(players[1].getY() - 2);
        }
        if (keysPressed.getOrDefault(KeyEvent.VK_DOWN, false)) {
            players[1].setY(players[1].getY() + 2);
        }
        if (keysPressed.getOrDefault(KeyEvent.VK_LEFT, false)) {
            players[1].setX(players[1].getX() - 2);
        }
        if (keysPressed.getOrDefault(KeyEvent.VK_RIGHT, false)) {
            players[1].setX(players[1].getX() + 2);
        }
        if (keysPressed.getOrDefault(KeyEvent.VK_W, false)) {
            players[0].setY(players[0].getY() - 2);
        }
        if (keysPressed.getOrDefault(KeyEvent.VK_S, false)) {
            players[0].setY(players[0].getY() + 2);
        }
        if (keysPressed.getOrDefault(KeyEvent.VK_A, false)) {
            players[0].setX(players[0].getX() - 2);
        }
        if (keysPressed.getOrDefault(KeyEvent.VK_D, false)) {
            players[0].setX(players[0].getX() + 2);
        }

        }




    private void botLimitCoordinates() throws InterruptedException {
        for (Bot bot : bots) {
            bot.limitBotCoordinates();
        }
    }

    private void updateTimer() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        int totalSeconds = (int) (elapsedTime / 1000);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        int milliseconds = (int) (elapsedTime % 1000);

        String timerText = String.format("%02d:%02d:%03d", minutes, seconds, milliseconds);
        timerLabel.setText(timerText);
    }

    private void showWinner(Player winner) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        String elapsedTimeFormatted = formatElapsedTime(elapsedTime);
        String message = " Oyuncu " + winner.getPlayerNumber() +"kazandı! "+
                "\nSüre: " + elapsedTimeFormatted;
        JOptionPane.showMessageDialog(this, message, "Oyun Bitti", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showBotWinner(Bot bot) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        String elapsedTimeFormatted = formatElapsedTime(elapsedTime);
        String message = " Bot " + bot.getBotNumber() +" kazandı!"+
                "\nSüre: " + elapsedTimeFormatted;
        JOptionPane.showMessageDialog(this, message, "Oyun Bitti", JOptionPane.INFORMATION_MESSAGE);
    }

    private String formatElapsedTime(long milliseconds) {
        long totalSeconds = milliseconds / 1000;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        long millis = milliseconds % 1000;

        return String.format("%02d:%02d:%03d", minutes, seconds, millis);
    }

    private void checkWinners() {
        for (Player player : players) {
            if (player.getX() > 20 && player.getX() < 170 && player.getY() > 390 && player.getY() < 400   ) {

                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;
                if(player.isPassed==true){
                    showWinner(player);
                    resetGame();

                    game = false;

                    return;

                }
            }
        }

        for (Bot bot : bots) {
            if (bot.getX() > 20 && bot.getX() < 170 && bot.getY() >390 && bot.getY() < 400) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;
                    showBotWinner(bot);
                    resetGame();

                    game = false;

                    return;





            }
        }
    }


    private void resetGame() {
        for (Player player : players) {
            player.resetPositionPlayer();
        }
        for (Bot bot : bots) {
            bot.resetPositionBot();
        }
    }


    public void game(int speed){
       for(Bot bot:bots){
           bot.setSpeed(speed);
       }
        repaint();
    }


    private void run() {
        player1.start();
        player2.start();
        bot1.start();
        bot2.start();
        bot3.start();
        bot4.start();
        bot5.start();

        while (game == true) {

            player1.crash(player2);
            player2.crash(player1);

            for (Bot bot : bots) {
                player1.crash(bot);
                player2.crash(bot);
            }

            bot1.crash(bot2);
            bot1.crash(bot3);
            bot1.crash(bot4);
            bot1.crash(bot5);

            bot2.crash(bot3);
            bot2.crash(bot4);
            bot2.crash(bot5);

            bot3.crash(bot4);
            bot3.crash(bot5);

            bot4.crash(bot5);

            updateTimer();

            handlePlayerMovements();
            checkWinners();
            try {
                botLimitCoordinates();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            gamePanel.repaint();

            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}