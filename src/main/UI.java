package main;

import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Font;

import res.ResourcePath;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font uiFont = ResourcePath.UI_FONT;
    Font messageFont = ResourcePath.MESSAGE_FONT;
    Font congratulationFont = ResourcePath.CONGRATULATIONS_FONT;
    //BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    UtilityTool uTool = new UtilityTool();

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");


    public UI(GamePanel gp) {

        this.gp = gp;
        //ITM_Key key = new ITM_Key(gp);
        //keyImage = key.image;

    }
    
    public void showMessage(String text) {

        message = text;
        messageOn = true;

    }


    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(uiFont);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState) {
            // DO PLAYSTATE STUFF
        }

        if(gp.gameState == gp.pauseState) {
            
            drawPauseScreen();

        }
    }

    public void drawPauseScreen() {

        g2.setFont(uiFont);
        String text = "PAUSED";
        int x = uTool.getXforCenteredText(text, g2, gp);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }


}
