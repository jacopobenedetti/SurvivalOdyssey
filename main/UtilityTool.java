package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

//In this class were written all of then utility functions

public class UtilityTool {

    /*
     * Scales a BufferedImage to the specified width and height.
     * 
     * @param original The original BufferedImage to scale.
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @return The scaled BufferedImage.
     */
    
    public BufferedImage scaledimage (BufferedImage original, int width, int height) {

        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics(); //Whatever g2
        g2.drawImage(original, 0, 0, width, height, null); //g2 draws an image on tilesize
        g2.dispose();

        return scaledImage;
    }
}
