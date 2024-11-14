package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ITM_Heart extends SuperItem {

    GamePanel gp;

    public ITM_Heart(GamePanel gp) {

        name = "Heart";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream(heartPath + "heart_full" + imageExtension));
            image2 = ImageIO.read(getClass().getResourceAsStream(heartPath + "heart_half" + imageExtension));
            image3 = ImageIO.read(getClass().getResourceAsStream(heartPath + "heart_blank" + imageExtension));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
        
    }
}
