/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import java.util.Random;
import main.Game;
import main.GamePanel;

/**
 *
 * @author Senai
 */
interface BinaryOperator {

    double apply(double a, double b);
}

public class Controller {

    public static final int tileSize = GamePanel.tileSize, playerHeight = tileSize, playerY = tileSize * 10;
    public static int xMove = 7, score = 0, playerX = xMove * tileSize, blockY, oldTime = 0, blockSpeed = 20000000, operacao = 0, maxBound = 4, iResposta;
    public static boolean blockMove = false, moving = true, next = true, correto = false;
    public static int vidas = 5;
    public static String[] respostas = new String[4];
    public static double pastTime = System.currentTimeMillis();
    public static Random random = new Random();
    public static String gameDifficulty, question = "", operator;
    public static int valor1 = random.nextInt((maxBound * 2)) - maxBound, valor2 = random.nextInt((maxBound * 2)) - maxBound;

    static BinaryOperator operador = getRandomOperator();
    public static double resultado = operador.apply(valor1, valor2);

    public void tick(boolean left, boolean right) {
        int oldX = xMove;
        switch (operacao) {
            case 0:
                operator = "+";
                break;
            case 1:
                operator = "-";
                break;
            case 2:
                operator = "*";
                break;
            case 3:
                operator = "/";
                break;
        }
        if (Game.time % blockSpeed == 0 && moving) {
            blockY += tileSize;
            oldTime = Game.time;
            pastTime = System.currentTimeMillis();
            if (blockY > (tileSize * 12)) {
                blockY = 0;
            }
        }
        if (!moving) {
            for (int i = 0; i < 50; i++) {
                double currentTime = System.currentTimeMillis();
                int cooldown = (int) (5 - (currentTime - pastTime) / 1000);

                if (cooldown <= 0) {
                    moving = true;
                    blockMove = false;
                    next = true;
                    correto = false;
                    blockY = 0;
                    valor1 = random.nextInt((maxBound * 2)) - maxBound;
                    valor2 = random.nextInt((maxBound * 2)) - maxBound;
                    operador = getRandomOperator();
                    resultado = operador.apply(valor1, valor2);
                    switch (operacao) {
                        case 0:
                            operator = "+";
                            break;
                        case 1:
                            operator = "-";
                            break;
                        case 2:
                            operator = "*";
                            break;
                        case 3:
                            operator = "/";
                            break;
                    }
                    
                }
            }
        }
        if (left && !blockMove && moving) {
            xMove -= 1;
            blockMove = true;
        }
        if (right && !blockMove && moving) {
            xMove += 1;
            blockMove = true;
            System.out.println(xMove);
        }
        if (!left && !right) {
            blockMove = false;
        }
        if (xMove != oldX) {
            playerX = xMove * tileSize;
        }
        if (playerX < 0) {
            playerX = 0;
            xMove = 0;
        }

        if (playerX > (tileSize * 16) - tileSize) {
            playerX = (tileSize * 16) - tileSize;
            xMove = 14;
        }

    }

    private static BinaryOperator getRandomOperator() {
        BinaryOperator[] operadores = new BinaryOperator[]{
            (a, b) -> a + b,
            (a, b) -> a - b,
            (a, b) -> a * b,
            (a, b) -> b != 0 ? a / b : 0
        };
        operacao = random.nextInt(operadores.length);
        return operadores[operacao];
    }
}
