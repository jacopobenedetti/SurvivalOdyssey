package item;

import entity.Entity;
import main.GamePanel;

public class ITM_Heart extends Entity {

    public ITM_Heart(GamePanel gp) {
        super(gp);
        
        name = "Heart";
        image = uTool.setupImage(heartPath, "heart_full", imageExtension, gp);
        image2 = uTool.setupImage(heartPath, "heart_half", imageExtension, gp);
        image3 = uTool.setupImage(heartPath, "heart_blank", imageExtension, gp);
        
    }
}
