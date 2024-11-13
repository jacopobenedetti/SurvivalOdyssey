package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import res.ResourcePath;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font uiFont = ResourcePath.UI_FONT;
    Font dialogueFont = ResourcePath.DIALOGUE_FONT_0;
    Font titleFont = ResourcePath.DIALOGUE_FONT_4;
    Color subWindowColor = ResourcePath.SUBWINDOW_COLOR;
    Color subWindowStrokeColor = ResourcePath.SUBWINDOW_STROKE_COLOR;
    String imageDavid = ResourcePath.DAVID_IMAGE_RESIZE_PATH;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    UtilityTool uTool = new UtilityTool();
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;




    public UI(GamePanel gp) {

        this.gp = gp;

    }
    
    public void showMessage(String text) {

        message = text;
        messageOn = true;

    }


    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(uiFont);
        g2.setColor(Color.white);

        //TITLE STATE

        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        } 

        // PLAY STATE
        if(gp.gameState == gp.playState) {
            // DO PLAYSTATE STUFF
        }

        //PAUSE STATE
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }

    public void drawDialogueScreen() {

        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(dialogueFont);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 16F));
        x += gp.tileSize;
        y += gp.tileSize;

        /* 
        for(String line: currentDialogue.split("\n")) { // EVERYTIME THER IS "\n" SIMBOL (IT CAN BE CHANGED), IT GOES TO THE TOP
            g2.drawString(line, x, y);
            y += 40;
        }
        */

        g2.drawString(currentDialogue, x, y);

    }

    public void drawSubWindow(int x, int y, int width, int height) {

        g2.setColor(subWindowColor);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        g2.setColor(subWindowStrokeColor);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public void drawPauseScreen() {

        g2.setFont(uiFont);
        String text = "PAUSED";
        int x = uTool.getXforCenteredText(text, g2, gp);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawTitleScreen() {

        if(titleScreenState == 0) {

            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            //TITLE NAME
            g2.setFont(titleFont);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));

            String text = "Survival Odyssey";

            int x = uTool.getXforCenteredText(text, g2, gp);
            int y = gp.tileSize * 3;

            //SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x + 3, y + 3);

            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //DAVID IMAGE
            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize * 2; 

            BufferedImage image = uTool.setupImage(imageDavid, "david", ".png", gp);
            g2.drawImage(image, x, y, gp.tileSize * 2, gp.tileSize * 2, null);


            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));

            text = "New Game";
            x = uTool.getXforCenteredText(text, g2, gp);
            y += gp.tileSize * 3.5 ;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Load Game";
            x = uTool.getXforCenteredText(text, g2, gp);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Quit";
            x = uTool.getXforCenteredText(text, g2, gp);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        } else if(titleScreenState == 1) {

            g2.setFont(dialogueFont);
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(48F));

            String text = "Select your class";

            int x = uTool.getXforCenteredText(text, g2, gp);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            g2.setFont(dialogueFont);
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(24F));

            text = "Fighter";
            x = uTool.getXforCenteredText(text, g2, gp);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            } 

            text = "Thief";
            x = uTool.getXforCenteredText(text, g2, gp);
            y += gp.tileSize;
            g2.drawString(text, x, y); 
            if(commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            } 

            text = "Sorcerer";
            x = uTool.getXforCenteredText(text, g2, gp);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Back";
            x = uTool.getXforCenteredText(text, g2, gp);
            y += gp.tileSize * 2;   
            g2.drawString(text, x, y);
            if(commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            } 
        }
    }
}
