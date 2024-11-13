package main;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import res.ResourcePath;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font uiFont = ResourcePath.UI_FONT;
    Font dialogueFont = ResourcePath.DIALOGUE_FONT;
    Color subWindowColor = ResourcePath.SUBWINDOW_COLOR;
    Color subWindowStrokeColor = ResourcePath.SUBWINDOW_STROKE_COLOR;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    UtilityTool uTool = new UtilityTool();
    public String currentDialogue = "";




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


}
