package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Boots extends SuperObject {

    GamePanel gp;
    
    public OBJ_Boots(GamePanel gp) {

        this.gp = gp;

        
        name = "Boots";

        try {
            image = ImageIO.read(getClass().getResourceAsStream(OBJ_PATH + "boots.png"));
            uTool.scaledimage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
