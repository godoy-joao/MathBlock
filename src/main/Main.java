/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JFrame;

/**
 *
 * @author Senai
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       JFrame frame = new JFrame();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setResizable(false);
       frame.setTitle("MathBlock");
       GamePanel game = new GamePanel();
       frame.add(game);
       frame.pack();
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
       game.start();
    }
    
}
