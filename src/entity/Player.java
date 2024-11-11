package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import res.ResourcePath;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;
    String path = ResourcePath.DAVID_IMAGE_PATH;
    String imageExtension = ResourcePath.fileImageExtension;

    //WHERE WE DRAW PLAYER ON THE SCREEN
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

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

        up1 = setup("david_up_1");
        up2 = setup("david_up_2");
        down1 = setup("david_down_1");
        down2 = setup("david_down_2");
        left1 = setup("david_left_1");
        left2 = setup("david_left_2");
        right1 = setup("david_right_1");
        right2 = setup("david_right_2");
        
    }

    public BufferedImage setup(String imageName) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {

            image = ImageIO.read(getClass().getResourceAsStream(path + imageName + imageExtension));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
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

                    gp.playSoundEffect(1);
                    hasKey++;
                    gp.itm[i] = null;
                    gp.ui.showMessage("You've got " + hasKey + " key now!");

                    break;

                case "Door":

                    if(hasKey > 0) {
                        gp.itm[i] = null;
                        hasKey--;
                        gp.playSoundEffect(4);
                        gp.ui.showMessage("You opened the door!");
                    } else {
                        gp.ui.showMessage("You need a key to open this door!");
                    }

                    break;
                
                case "Boots":

                    speed += 1;
                    gp.itm[i] = null;
                    gp.playSoundEffect(2);
                    gp.ui.showMessage("Now you can walk faster!");

                    break;

                case "Chest":

                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSoundEffect(3);
                    
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

        g2.drawImage(image, screenX, screenY, null);
    }


}
