package item;

import java.awt.Graphics2D;
<<<<<<< HEAD
import java.awt.image.BufferedImage;

import main.GamePanel;
=======
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import res.ResourcePath;
>>>>>>> d3039c5

public class SuperItem {

    // Directory path for item images and file extension for image files
<<<<<<< HEAD
    public String filePath = "/res/items/";
    public String fileExtension = ".png";
=======
    String path = ResourcePath.ITEMS_IMAGE_PATH;
    String imageExtension = ResourcePath.fileImageExtension;
>>>>>>> d3039c5

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
<<<<<<< HEAD
=======
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
>>>>>>> d3039c5

    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);


        }
    }
}