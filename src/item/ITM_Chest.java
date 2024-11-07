package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ITM_Chest extends SuperItem {

    public ITM_Chest() {
        name = "Chest";
        
        try {
<<<<<<< HEAD
            image = ImageIO.read(getClass().getResourceAsStream(filePath + name + fileExtension));
=======
            image = ImageIO.read(getClass().getResourceAsStream(path + name + imageExtension));
>>>>>>> d3039c5
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    
}
