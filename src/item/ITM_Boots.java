package item;

import entity.Entity;
import main.GamePanel;

public class ITM_Boots extends Entity {

    public ITM_Boots(GamePanel gp) {
        super(gp);

        name = "Boots";
        down1 = uTool.setupImage(itemsPtah, name, imageExtension, gp);
        
    }
}

