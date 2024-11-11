package item;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class ITM_Boots extends SuperItem {

    GamePanel gp;

    public ITM_Boots(GamePanel gp) {

        name = "Boots";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path + name + imageExtension));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

