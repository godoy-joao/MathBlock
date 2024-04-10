/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import input.InputHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

/**
 *
 * @author Senai
 */
public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;    
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    
    private Thread thread;
    private boolean running = false;
    private InputHandler input;
    private Game game;
    
    public GamePanel() {
        Dimension size = new Dimension(screenWidth, screenHeight);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setBackground(Color.black);
        input = new InputHandler();
        game = new Game();
        addKeyListener(input);
        setFocusable(true);
        
    }
    
    public void start() {
        if(running)return;
        thread = new Thread(this);
        running = true;
        thread.start();
    }
    
    public void stop() {
        if(!running)return;
        running = false;
        try {
            thread.join();
        } catch(InterruptedException e) {
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
        
        Graphics2D g2 = (Graphics2D)g;
        
        g2.setColor(Color.red);
        g2.fillRect(game.controls.x, 100, 100, 40);
        g2.dispose();
    }
}
