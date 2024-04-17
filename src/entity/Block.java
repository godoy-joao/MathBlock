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
        game.controls.question = "(" + game.controls.valor1 + ")" + " " + game.controls.operator + " " + "(" + game.controls.valor2 + ")";
        List<Rectangle> rects = new ArrayList();
        game.controls.gameDifficulty = "easy";
        if (game.controls.score >= 2000 && game.controls.score < 5000) {
            game.controls.gameDifficulty = "medium";
        } else if (game.controls.score >= 5000) {
            game.controls.gameDifficulty = "hard";
        }
        switch (game.controls.gameDifficulty) {
            case "easy":
                game.controls.blockSpeed = 20000000;
                game.controls.maxBound = 5;
                break;
            case "medium":
                game.controls.blockSpeed = 13000000;
                game.controls.maxBound = 10;
                break;
            case "hard":
                game.controls.blockSpeed = 7000000;
                game.controls.maxBound = 40;
                break;
        }
        Random random = new Random();
        if (game.controls.next) {
            for (int i = 0; i < 4; i++) {
                game.controls.respostas[i] = String.format("%.2f", (random.nextDouble() * game.controls.maxBound * 6 + 1));
                System.out.println(game.controls.respostas[i]);
            }
            boolean temResposta = false;
            for (int i = 0; i < game.controls.respostas.length; i++) {
                if (Double.parseDouble(game.controls.respostas[i].replace(",", ".")) == game.controls.resultado) {
                    temResposta = true;
                }
            }
            if (!temResposta) {
                game.controls.iResposta = random.nextInt(4);
                game.controls.respostas[game.controls.iResposta] = String.format("%.2f", game.controls.resultado);
            }
            game.controls.next = false;
        }

        for (int i = 0; i < 4; i++) {

            Rectangle rect = new Rectangle();
            rect.setBounds(i * game.controls.tileSize * 4 + game.controls.tileSize, game.controls.blockY, game.controls.tileSize * 2, game.controls.tileSize);
            int x = (int) rect.getX();
            if ((game.controls.playerX >= x - game.controls.tileSize && game.controls.playerX <= x + game.controls.tileSize * 2 + 1) && game.controls.blockY == game.controls.playerY && game.controls.moving) {
                game.controls.moving = false;
                game.controls.blockMove = true;
                game.controls.pastTime = System.currentTimeMillis();
                System.out.println(game.controls.iResposta * game.controls.tileSize * 4 + game.controls.tileSize);
                int xResposta = game.controls.iResposta * game.controls.tileSize * 4 + game.controls.tileSize;
                if (game.controls.playerX >= xResposta && game.controls.playerX < xResposta + game.controls.tileSize * 2) {
                    game.controls.score += 200;
                    game.controls.correto = true;
                } else {
                    game.controls.vidas -= 1;
                }
            }
            rects.add(rect);
        }
        return rects;
    }
    List<Rectangle> retangulos = tick();
}
