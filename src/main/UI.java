package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Font;

import res.ResourcePath;
import item.ITM_Key;

public class UI {

    GamePanel gp;
    Font uiFont = ResourcePath.UI_FONT;
    Font messageFont = ResourcePath.MESSAGE_FONT;
    Font congratulationFont = ResourcePath.CONGRATULATIONS_FONT;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");


    public UI(GamePanel gp) {

        this.gp = gp;
        ITM_Key key = new ITM_Key(gp);
        keyImage = key.image;

    }
    
    public void showMessage(String text) {

        message = text;
        messageOn = true;

    }


    public void draw(Graphics2D g2) {

        if(gameFinished) {

            // DISPLAYS TEXT MESSAGE
            g2.setFont(uiFont);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x, y;
    
            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // RETURNS LENGTH OF THE TEXT

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);

            g2.drawString(text, x, y);

            // DISPLAYS TIME
            g2.setFont(uiFont);
            g2.setColor(Color.white);
    
            text = "Your time is " + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // RETURNS LENGTH OF THE TEXT

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);

            g2.drawString(text, x, y);

            // DISPLAYS CONGRATULATIONS
            g2.setFont(congratulationFont);
            g2.setColor(Color.yellow);

            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // RETURNS LENGTH OF THE TEXT

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);

            g2.drawString(text, x, y);  
            

            gp.gameThread = null;

        } else {

            g2.setFont(uiFont);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey , 74, 65);

            //TIME
            playTime += (double)1/60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 65);

            if(messageOn) {

                g2.setFont(messageFont);
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageCounter++;

                if(messageCounter > 120) { // IN ABOUT TWO SECONDS THE MESSAGE DISAPPEARS

                    messageCounter = 0;
                    messageOn = false;

                }
            }
        }
    }
}
