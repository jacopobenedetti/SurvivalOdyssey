package item;

import entity.Entity;
import main.GamePanel;

public class ITM_Door extends Entity {

    GamePanel gp;

    public ITM_Door(GamePanel gp) {
        super(gp);

        name = "Boots";
        down1 = uTool.setupImage(itemsPtah, name, imageExtension, gp);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y; 
        
    }
}
