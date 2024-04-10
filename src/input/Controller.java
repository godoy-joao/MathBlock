/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

/**
 *
 * @author Senai
 */
public class Controller {

    public static int x, y, xMove, yMove;
    static boolean blockMove = false;

    public void tick(boolean left, boolean right) {
        int oldX = xMove;
       
        if (left && blockMove == false) {
            xMove -= 1;
            blockMove = true;
        }
        if (right && !blockMove) {
            xMove += 1;
            blockMove = true;
        }
        if (!left && !right) {
            blockMove = false;
        }
        if (xMove != oldX) {
        x = xMove * 50;
        }
        System.out.println(x);
    }
}
