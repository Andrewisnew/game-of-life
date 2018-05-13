package com.epam;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class Area extends JPanel {

    private static final int SCALE = 20;
    private static int WIDTH;
    private static int HEIGHT;
    private static boolean[][] state;
    private static byte[][] battleState;

    private static GameMode gameMode;

    public Area() {
    }

    @Override
    public void paint(Graphics g) {
        switch (gameMode) {
            case SURVIVAL:
                for (int i = 0; i < WIDTH; i++) {
                    for (int j = 0; j < HEIGHT; j++) {
                        g.setColor(state[i][j] ? color(0, 0, 255) : color(255, 200, 200));
                        g.fillRect(j * SCALE, i * SCALE, WIDTH * SCALE, HEIGHT * SCALE);
                    }
                }
                break;
            case BATTLE:
                for (int i = 0; i < WIDTH; i++) {
                    for (int j = 0; j < HEIGHT; j++) {
                        Color color = color(255, 200, 200);
                        switch (battleState[i][j]) {
                            case 0:
                                color = color(255, 200, 200);
                                break;
                            case 1:
                                color = color(0, 0, 255);
                                break;
                            case 2:
                                color = color(0, 255, 0);
                                break;
                            case 3:
                                color = color(255, 0, 0);
                                break;
                            case 4:
                                color = color(255, 255, 0);
                                break;
                        }
                        g.setColor(color);
                        g.fillRect(j * SCALE, i * SCALE, WIDTH * SCALE, HEIGHT * SCALE);
                    }
                }
        }
        g.setColor(color(255, 255, 255));
        for (int xx = 0; xx <= WIDTH * SCALE; xx += SCALE) {
            g.drawLine(xx, 0, xx, HEIGHT * SCALE);
        }
        for (int yy = 0; yy <= HEIGHT * SCALE; yy += SCALE) {
            g.drawLine(0, yy, WIDTH * SCALE, yy);
        }
    }

    public static void initState() {
        WIDTH = 30;
        HEIGHT = 30;
        switch (gameMode) {
            case SURVIVAL: {
                boolean[][] initState = new boolean[WIDTH][HEIGHT];
                for (int i = 0; i < WIDTH; i++) {
                    for (int j = 0; j < HEIGHT; j++) {
                        initState[i][j] = Math.random() < 0.5;
                    }
                }
                state = initState;
                break;
            }
            case BATTLE: {
                byte[][] initState = new byte[WIDTH][HEIGHT];
                for (int i = 2; i < WIDTH / 3; i++) {
                    for (int j = 2 * HEIGHT / 3; j < HEIGHT - 2; j++) {
                        initState[i][j] = (byte) (Math.random() < 0.5 ? 1 : 0);
                    }
                }
                for (int i = 2 * WIDTH / 3; i < WIDTH - 2; i++) {
                    for (int j = 2; j < HEIGHT / 3; j++) {
                        initState[i][j] = (byte) (Math.random() < 0.5 ? 2 : 0);
                    }
                }
                for (int i = 2; i < WIDTH / 3; i++) {
                    for (int j = 2; j < HEIGHT / 3; j++) {
                        initState[i][j] = (byte) (Math.random() < 0.5 ? 3 : 0);
                    }
                }
                for (int i = 2 * WIDTH / 3; i < WIDTH - 2; i++) {
                    for (int j = 2 * HEIGHT / 3; j < HEIGHT - 2; j++) {
                        initState[i][j] = (byte) (Math.random() < 0.5 ? 4 : 0);
                    }
                }

                battleState = initState;
                break;
            }
        }
    }


    public Color color(int red, int green, int blue) {
        return new Color(red, green, blue);
    }


    public static void main(String[] args) throws InterruptedException {
        gameMode = GameMode.BATTLE;
        initState();
        JFrame f = new JFrame("Life");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(true);

        f.add(new Area());
        f.setSize(WIDTH * SCALE + 17, HEIGHT * SCALE + 39);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        GameEngine game = new GameEngineImpl();
        TimeUnit.SECONDS.sleep(3);
        while (true) {
            battleState = game.compute(battleState, 1);
            f.repaint();
            TimeUnit.MILLISECONDS.sleep(100);

        }


    }
}