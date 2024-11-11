package item;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class ITM_Key extends SuperItem {

    GamePanel gp;
    
    public ITM_Key(GamePanel gp) {
        
        name = "Key";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path + name + imageExtension));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
