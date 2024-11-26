package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;
import res.ResourcePath;

public class MON_GreenSlime extends Entity {


    UtilityTool uTool = new UtilityTool();
    String monsterPath = ResourcePath.MONSTER_IMAGE_PATH;
    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {

        super(gp);

        type = 2;
        name = "GreenSlime";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();

    }

    public void getImage() {

        up1 = uTool.setupImage(monsterPath, "greenslime_down_1", imageExtension, gp);
        up2 = uTool.setupImage(monsterPath, "greenslime_down_2", imageExtension, gp);
        down1 = uTool.setupImage(monsterPath, "greenslime_down_1", imageExtension, gp);
        down2 = uTool.setupImage(monsterPath, "greenslime_down_2", imageExtension, gp);
        left1 = uTool.setupImage(monsterPath, "greenslime_down_1", imageExtension, gp);
        left2 = uTool.setupImage(monsterPath, "greenslime_down_2", imageExtension, gp);
        right1 = uTool.setupImage(monsterPath, "greenslime_down_1", imageExtension, gp);
        right2 = uTool.setupImage(monsterPath, "greenslime_down_2", imageExtension, gp);

    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {

            Random random = new Random();

            int i = random.nextInt(100) + 1; // pick up a number from 1 to 100;

            if (i <= 25) {
                direction = "up";
            }

            if (i > 25 && i <= 50) {
                direction = "down";
            }

            if (i > 50 && i <= 75) {
                direction = "left";
            }

            if (i > 75) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
}