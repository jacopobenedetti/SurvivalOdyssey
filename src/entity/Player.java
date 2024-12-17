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

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();

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

        up1 = uTool.setupImage(path, "david_up_1", imageExtension, gp, gp.tileSize, gp.tileSize);
        up2 = uTool.setupImage(path, "david_up_2", imageExtension, gp, gp.tileSize, gp.tileSize);
        down1 = uTool.setupImage(path, "david_down_1", imageExtension, gp, gp.tileSize, gp.tileSize);
        down2 = uTool.setupImage(path, "david_down_2", imageExtension, gp, gp.tileSize, gp.tileSize);
        left1 = uTool.setupImage(path, "david_left_1", imageExtension, gp, gp.tileSize, gp.tileSize);
        left2 = uTool.setupImage(path, "david_left_2", imageExtension, gp, gp.tileSize, gp.tileSize);
        right1 = uTool.setupImage(path, "david_right_1", imageExtension, gp, gp.tileSize, gp.tileSize);
        right2 = uTool.setupImage(path, "david_right_2", imageExtension, gp, gp.tileSize, gp.tileSize);

    }

    public void getPlayerAttackImage() {

        attackUp1 = uTool.setupImage(path, "david_attack_up_1", imageExtension, gp, gp.tileSize, gp.tileSize * 2);
        attackUp2 = uTool.setupImage(path, "david_attack_up_2", imageExtension, gp, gp.tileSize, gp.tileSize * 2);
        attackDown1 = uTool.setupImage(path, "david_attack_down_1", imageExtension, gp, gp.tileSize, gp.tileSize * 2);
        attackDown2 = uTool.setupImage(path, "david_attack_down_2", imageExtension, gp, gp.tileSize, gp.tileSize * 2);
        attackLeft1 = uTool.setupImage(path, "david_attack_left_1", imageExtension, gp, gp.tileSize * 2, gp.tileSize);
        attackLeft2 = uTool.setupImage(path, "david_attack_left_2", imageExtension, gp, gp.tileSize * 2, gp.tileSize);
        attackRight1 = uTool.setupImage(path, "david_attack_right_1", imageExtension, gp, gp.tileSize * 2, gp.tileSize);
        attackRight2 = uTool.setupImage(path, "david_attack_right_2", imageExtension, gp, gp.tileSize * 2, gp.tileSize);

    }

    public void update() {

        if(attacking) {
            attacking();

        } 
        else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true  || keyH.enterPressed == true) {

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

            if (!collisionOn && !keyH.enterPressed) {

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

            gp.keyH.enterPressed = false; 

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

    public void attacking() {
        spriteCounter++; 

        if(spriteCounter <= 5) {
            spriteNum = 1;
        }
        
        if(spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            // We check where the weapon is. To check this we have to temporarily modify player's coordinate.

            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea
            switch (direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // Check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            // After checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }

        if(spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpItem(int i) {

        if (i != 999) {

        }
    }

    public void interactNPC(int i) {

        if(gp.keyH.enterPressed) {
            if (i != 999) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
                
            } else {
                attacking = true;
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

    public void damageMonster(int i) {

        if(i != 999) {

            if(!gp.monster[i].invincible) {

                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;

                if(gp.monster[i].life <= 0) {
                    gp.monster[i] = null;
                }
            }
        } 
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int x = screenX;
        int y = screenY;

        switch (direction) {
            case "up":
            if(!attacking) {
                if (spriteNum == 1) { image = up1; }
                if (spriteNum == 2) { image = up2; }
            }
            if(attacking) {
                y = screenY - gp.tileSize;
                if (spriteNum == 1) { image = attackUp1; }
                if (spriteNum == 2) { image = attackUp2; }
            }
            break;
            case "down":
            if (!attacking) {
                if (spriteNum == 1) { image = down1; }
                if (spriteNum == 2) { image = down2; }
            }
            if (attacking) {
                if (spriteNum == 1) { image = attackDown1; }
                if (spriteNum == 2) { image = attackDown2; }
            }
            break;
            case "left":
            if (!attacking) {
                if (spriteNum == 1) { image = left1; }
                if (spriteNum == 2) { image = left2; }
            }
            if (attacking) {
                x = screenX - gp.tileSize;
                if (spriteNum == 1) { image = attackLeft1; }
                if (spriteNum == 2) { image = attackLeft2; }
            }
            break;
            case "right":
            if (!attacking) {
                if (spriteNum == 1) { image = right1; }
                if (spriteNum == 2) { image = right2; }
            }
            if (attacking) {
                if (spriteNum == 1) { image = attackRight1; }
                if (spriteNum == 2) { image = attackRight2; }
            }
            break;
        }
        if(invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

        }

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
