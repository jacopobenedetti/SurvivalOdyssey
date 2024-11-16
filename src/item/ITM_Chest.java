package item;

import entity.Entity;
import main.GamePanel;

public class ITM_Chest extends Entity {

    GamePanel gp;

    public ITM_Chest(GamePanel gp) {
        super(gp);

        name = "Chest";
        down1 = uTool.setupImage(itemsPtah, name, imageExtension, gp);


    }
    
}
