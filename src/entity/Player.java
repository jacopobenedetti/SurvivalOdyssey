package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import res.ResourcePath;

public class Player extends Entity {

    KeyHandler keyH;
    String path = ResourcePath.DAVID_IMAGE_PATH;
    String imageExtension = ResourcePath.fileImageExtension;
    UtilityTool uTool = new UtilityTool();

    // WHERE WE DRAW PLAYER ON THE SCREEN
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

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

    public void setDefaultValues() {

        // STARTING POSITION
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;

        speed = 4;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6; // 1 means half heart, 2 means 1 complete heart
        life = maxLife;

    }

    public void getPlayerImage() {

        up1 = uTool.setupImage(path, "david_up_1", imageExtension, gp);
        up2 = uTool.setupImage(path, "david_up_2", imageExtension, gp);
        down1 = uTool.setupImage(path, "david_down_1", imageExtension, gp);
        down2 = uTool.setupImage(path, "david_down_2", imageExtension, gp);
        left1 = uTool.setupImage(path, "david_left_1", imageExtension, gp);
        left2 = uTool.setupImage(path, "david_left_2", imageExtension, gp);
        right1 = uTool.setupImage(path, "david_right_1", imageExtension, gp);
        right2 = uTool.setupImage(path, "david_right_2", imageExtension, gp);

    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK ITEM COLLISON
            int itmIndex = gp.cChecker.checkItem(this, true);
            pickUpItem(itmIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // CHECK EVENT COLLISION
            gp.eHandler.checkEvent();

            gp.keyH.enterPressed = false;

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
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
         
        if(invincible) {
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void pickUpItem(int i) {

        if (i != 999) {

        }
    }

    public void interactNPC(int i) {

        if (i != 999) {
            if (gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void contactMonster(int i) {

        if(i != 999) {
            if(!invincible) {
                life -= 1;
                invincible = true; 
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        if(invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

        }

        int x = screenX;
        int y = screenY;

        if(screenX > worldX) {
            x = worldX;
        }
        if(screenY > worldY) {
            y = worldY;
        }

        int rightOffset = gp.screenWidth - screenX;
        if(rightOffset > gp.worldWidth - worldX) {
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }
        int bottomOffset = gp.screenHeight - screenY;
        if(bottomOffset > gp.worldHeight - worldY) {
            y = gp.screenHeight - (gp.worldHeight - worldY);
        }

        

        g2.drawImage(image, x, y, null);

        // RESET ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
