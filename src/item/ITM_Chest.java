package item;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class ITM_Chest extends SuperItem {

    GamePanel gp;

    public ITM_Chest(GamePanel gp) {
        
        name = "Chest";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path + name + imageExtension));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    
}
