package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import res.ResourcePath;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;
    String path = ResourcePath.DAVID_IMAGE_PATH;

    //WHERE WE DRAW PLAYER ON THE SCREEN
    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public Player (GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues () {
        
        //STARTING POSITION
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;

        speed = 4;
        direction = "down";

    }
    
    public void getPlayerImage() {


        try {

            up1 = ImageIO.read(getClass().getResourceAsStream(path + "david_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream(path + "david_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream(path + "david_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream(path + "david_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream(path + "david_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream(path + "david_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream(path + "david_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream(path + "david_right_2.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyH.upPressed == true|| keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            int itmIndex = gp.cChecker.checkItem(this, true);
            pickUpItem(itmIndex);


            if (!collisionOn) {

                switch (direction) {

                    case "up":

                        worldY -= speed;

                        break;

                    case "down":

                        worldY += speed;

                        break;

                    case "left":

                        worldX -= speed;

                        break;

                    case "right":
                    
                        worldX += speed;
                    
                        break;

                }
            }
    
            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            
        }


    }

    public void pickUpItem(int i) {

        if(i != 999) {

            String itemName = gp.itm[i].name;

            switch(itemName) {
                case "Key":

                    hasKey++;
                    gp.itm[i] = null;

                    break;

                case "Door":

                    if(hasKey > 0) {
                        gp.itm[i] = null;
                        hasKey--;
                    }

                    break;

            }

        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left": 
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }


}
