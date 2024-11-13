package entity;

import java.util.Random;

import main.GamePanel;
import main.UtilityTool;
import res.ResourcePath;

public class NPC_Robot extends Entity {

    UtilityTool uTool = new UtilityTool();
    String path = ResourcePath.NPC_IMAGE_PATH;
    String imageExtension = ResourcePath.fileImageExtension;

    public NPC_Robot(GamePanel gp) {

        super(gp);

        direction = "down";
        speed = 1;

        getImage();
    }

    public void getImage() {

        up1 = uTool.setupImage(path,"oldman_up_1", imageExtension, gp);
        up2 = uTool.setupImage(path,"oldman_up_2", imageExtension, gp);
        down1 = uTool.setupImage(path,"oldman_down_1", imageExtension, gp);
        down2 = uTool.setupImage(path,"oldman_down_2", imageExtension, gp);
        left1 = uTool.setupImage(path,"oldman_left_1", imageExtension, gp);
        left2 = uTool.setupImage(path,"oldman_left_2", imageExtension, gp);
        right1 = uTool.setupImage(path,"oldman_right_1", imageExtension, gp);
        right2 = uTool.setupImage(path,"oldman_right_2", imageExtension, gp);

    }

    public void setAction() { //WE WANT THAT EVERY CHARACTERS HAVE DIFFERENTE BEHAVIORS SO WE SET ACTION IN THIS SUBCLASS
        
        actionLockCounter++; 

        if(actionLockCounter == 120) {

            Random random = new Random();

            int i = random.nextInt(100) + 1; // pick up a number from 1 to 100;
    
            if(i <= 25) {
    
                direction = "up";
    
            }
    
            if(i > 25 && i <= 50) {
    
                direction = "down";
    
            }
    
            if(i > 50 && i <= 75) {
    
                direction = "left";
    
            }
    
            if(i > 75) {
    
                direction = "right";
    
            }

            actionLockCounter = 0;
        }
    }
        
}
