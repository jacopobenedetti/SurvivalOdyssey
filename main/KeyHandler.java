package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/*
* Main Functions:
* 1. keyTyped(KeyEvent e): This method is required by the KeyListener interface but is 
*    not used in this implementation.
* 2. keyPressed(KeyEvent e): This method captures key press events and sets corresponding 
*    boolean flags to true, enabling movement and other actions.
* 3. keyReleased(KeyEvent e): This method captures key release events and sets corresponding 
*    boolean flags to false, stopping movement and other actions.
* 4. KeyHandler(GamePanel gp): The constructor initializes the KeyHandler with a reference 
*    to the GamePanel, enabling it to interact with the game's state.
*/

public class KeyHandler implements KeyListener{

    GamePanel gp;
    public boolean upPressed, downPressed, rightPressed, leftPressed, shiftPressed;
    
    //DEBUG
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }
        if(code == KeyEvent.VK_P || code == KeyEvent.VK_ESCAPE) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;            
            }
        }

        //DEBUG
        if(code == KeyEvent.VK_T) {
            if(!checkDrawTime) {
                checkDrawTime = true;
            } else if(checkDrawTime){
                checkDrawTime = false;
            }
        }



    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();


        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }

    }
    
}
