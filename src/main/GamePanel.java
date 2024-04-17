/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.Block;
import entity.Player;
import input.InputHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Senai
 *
 */
interface BinaryOperator {

    int apply(int a, int b);
}

public class GamePanel extends JPanel implements Runnable {

    BinaryOperator[] operador = new BinaryOperator[]{
        (a, b) -> a + b,
        (a, b) -> a - b,
        (a, b) -> a * b,
        (a, b) -> b != 0 ? a / b : 0
    };

    static final int originalTileSize = 16;
    static final int scale = 3;
    public static final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    private Thread thread;
    private boolean running = false;
    private InputHandler input;
    private Game game;
    private Block blocks;
    private Player player;

    public GamePanel() {
        Dimension size = new Dimension(screenWidth, screenHeight);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setBackground(Color.black);
        input = new InputHandler();
        game = new Game();
        blocks = new Block();
        player = new Player();
        addKeyListener(input);
        setFocusable(true);

    }

    public void start() {
        if (running) {
            return;
        }
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.exit(0);
        }

    }

    @Override
    public void run() {
        while (running) {
            tick();
            repaint();
        }
    }

    public void tick() {
        game.tick(input.key);
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("Arial", 1, 20);
        g.setFont(font);
        
        Color playerColor = new Color(0xFFaFFa);
        Color blockColor = new Color(0xFF44a0);
        g2.setColor(blockColor);
        List<Rectangle> retangulos = blocks.tick();
        for (int i = 0; i < retangulos.size(); i++) {
            g2.fill(retangulos.get(i));
        }
        g2.setColor(playerColor);
        g2.fill(player.tick());
        g.setColor(Color.white);
        g.drawString("Score: "+Integer.toString(game.controls.score), screenWidth/2 - tileSize - font.getSize(), game.controls.playerY + 80);

        g2.dispose();
    }
}
