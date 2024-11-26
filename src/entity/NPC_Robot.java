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
        setDialogue();
        
    }

    public void getImage() {

        up1 = uTool.setupImage(path, "oldman_up_1", imageExtension, gp);
        up2 = uTool.setupImage(path, "oldman_up_2", imageExtension, gp);
        down1 = uTool.setupImage(path, "oldman_down_1", imageExtension, gp);
        down2 = uTool.setupImage(path, "oldman_down_2", imageExtension, gp);
        left1 = uTool.setupImage(path, "oldman_left_1", imageExtension, gp);
        left2 = uTool.setupImage(path, "oldman_left_2", imageExtension, gp);
        right1 = uTool.setupImage(path, "oldman_right_1", imageExtension, gp);
        right2 = uTool.setupImage(path, "oldman_right_2", imageExtension, gp);

    }

    public void setDialogue() {

        dialogues[0] = "Today it's a very nice day!";
        dialogues[1] = "The sun is shining brightly outside.";
        dialogues[2] = "I think I'll go for a walk later.";
        dialogues[3] = "Maybe I'll grab a coffee on the way.";
        dialogues[4] = "Have you heard about the new bookstore?";
        dialogues[5] = "I plan to visit it sometime this week.";
        dialogues[6] = "Reading a good book is always relaxing.";
        dialogues[7] = "My favorite genre is science fiction.";
        dialogues[8] = "Although, I also enjoy mysteries a lot.";
        dialogues[9] = "Sometimes, I wish I could write a book.";
        dialogues[10] = "It would be about adventure and discovery.";
        dialogues[11] = "Exploring unknown lands sounds exciting!";
        dialogues[12] = "But first, I'll need to find the time.";
        dialogues[13] = "Life can get pretty busy, can't it?";
        dialogues[14] = "It's important to take breaks now and then.";
        dialogues[15] = "Maybe I'll start a journal instead.";
        dialogues[16] = "Journals are a good way to reflect on life.";
        dialogues[17] = "You can capture moments and memories.";
        dialogues[18] = "Looking back, it's amazing how things change.";

    }

    public void setAction() { // WE WANT THAT EVERY CHARACTERS HAVE DIFFERENTE BEHAVIORS SO WE SET ACTION IN
                              // THIS SUBCLASS

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

    public void speak() {
        super.speak();
    }

}
