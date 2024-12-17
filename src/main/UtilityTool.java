package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.imageio.ImageIO;

import res.ResourcePath;

public class UtilityTool {

    static String fontPath = ResourcePath.FONT_PATH;
    static String fileFontExtension = ResourcePath.fileFontExtension;


    
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {

        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);

        return scaledImage;
    }

    public BufferedImage setupImage (String path, String imageName, String extension, GamePanel gp, int width, int height) {

        BufferedImage image = null;

        try {

            image = ImageIO.read(getClass().getResourceAsStream(path + imageName + extension));
            image = scaleImage(image, width, height);

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

    public static Font importFont(Font font, String fileName) {

        InputStream is = ResourcePath.class.getResourceAsStream(fontPath + fileName + fileFontExtension);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        return font;
    }

    /*
    public void writeString (String text, Graphics2D g2, , Font font) {


    }

    */

}
