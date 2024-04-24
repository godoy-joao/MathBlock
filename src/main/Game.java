/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import input.Controller;
import java.awt.event.KeyEvent;

/**
 *
 * @author Senai
 */
public class Game {

    public static int time, vidas = 5;

    public Controller controls;

    public Game() {
        controls = new Controller();
    }

    public void tick(boolean[] key) {
        time++;

        if (time >= Integer.MAX_VALUE) {
            time = 0;
        }
        boolean left = key[KeyEvent.VK_A] || key[KeyEvent.VK_LEFT];
        boolean right = key[KeyEvent.VK_D] || key[KeyEvent.VK_RIGHT];
        boolean down = key[KeyEvent.VK_S] || key[KeyEvent.VK_DOWN];
        boolean up = key[KeyEvent.VK_W] || key[KeyEvent.VK_UP];

        controls.tick(left, right, down, up);
    }

}
