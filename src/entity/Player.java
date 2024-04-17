/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.awt.Color;
import java.awt.Rectangle;
import main.Game;

/**
 *
 * @author João Guilherme
 */
public class Player {
    
    Rectangle rect = new Rectangle();
    Game game = new Game();
    
    public Rectangle tick() {       
        rect.setBounds(game.controls.playerX, game.controls.playerY, game.controls.tileSize, game.controls.playerHeight);
        return rect;
    }
}
