package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class UtilityTool {

    
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {

        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);

        return scaledImage;
    }

    public BufferedImage setupImage (String path, String imageName, String extension, GamePanel gp) {

        BufferedImage image = null;

        try {

            image = ImageIO.read(getClass().getResourceAsStream(path + imageName + extension));
            image = scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    public int getXforCenteredText(String text, Graphics2D g2, GamePanel gp) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        
        return x;
    }

}
