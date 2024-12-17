package item;

import entity.Entity;
import main.GamePanel;

public class ITM_Key extends Entity {

    GamePanel gp;
    
    public ITM_Key(GamePanel gp) {
        super(gp);

        name = "Boots";
        down1 = uTool.setupImage(itemsPtah, name, imageExtension, gp, gp.tileSize, gp.tileSize);
    }
}
