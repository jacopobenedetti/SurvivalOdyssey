package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

/*
* Main Functions:
* 1. Constructor (`UI`): Initializes the font settings and references the GamePanel.
* 2. showMessage(String text): Displays a message on the screen.
* 3. draw(Graphics2D g2): Handles the drawing of the UI elements based on the game state.
* 4. drawPauseScreen(): Renders the pause screen when the game is paused.
*/

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Courier", Font.PLAIN, 40);
        arial_80B = new Font("Courier", Font.BOLD, 80);
        //OBJ_Key key = new OBJ_Key(gp);
        //keyImage = key.image;

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState) {
            // Do play state stuff
        }
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen () {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));
        String text = "PAUSED";
        int x = gp.screenHeight/2;
        int y = gp.screenHeight/2; //to display text in the middle of the screen

        g2.drawString(text, x, y);
    }
}
