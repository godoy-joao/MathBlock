/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import main.Game;

/**
 *
 * @author João Guilherme
 */
public class Block {

    Game game = new Game();


    public List<Rectangle> tick() {
        List<Rectangle> rects = new ArrayList();
        String difficulty = "easy";
        if (game.controls.score > 2500 && game.controls.score < 10000) {
            difficulty = "medium";
        } else if (game.controls.score >= 10000) {
            difficulty = "hard";
        }
        switch (difficulty) {
            case "easy":
                game.controls.blockSpeed = 10000000;
                game.controls.maxBound = 72;
                break;
            case "medium":
                game.controls.blockSpeed = 8000000;
                game.controls.maxBound = 100; 
                break;
            case "hard":
                game.controls.blockSpeed = 3000000;
                game.controls.maxBound = 1384;
                break;
        }
        
        for (int i = 0; i < 4; i++) {
            Rectangle rect = new Rectangle();
            rect.setBounds(i * game.controls.tileSize * 4 + game.controls.tileSize, game.controls.blockY, game.controls.tileSize * 2, game.controls.tileSize);
            int x = (int) rect.getX();
            if ((game.controls.playerX >= x - game.controls.tileSize && game.controls.playerX <= x + game.controls.tileSize * 2 + 1) && game.controls.blockY == game.controls.playerY && game.controls.moving) {
                game.controls.moving = false;
                game.controls.blockMove = true;
                game.controls.pastTime = System.currentTimeMillis();
            }
            rects.add(rect);
        }
        return rects;
    }
    List<Rectangle> retangulos = tick();
}
