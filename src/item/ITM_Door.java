package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ITM_Door extends SuperItem {

    public ITM_Door() {

        name = "Door";
        
        try {
<<<<<<< HEAD
            image = ImageIO.read(getClass().getResourceAsStream(filePath + name + fileExtension));
=======
            image = ImageIO.read(getClass().getResourceAsStream(path + name + imageExtension));
>>>>>>> d3039c5
        } catch (IOException e) {
            e.printStackTrace();
        }

<<<<<<< HEAD
=======
        collision = true;
        
>>>>>>> d3039c5
    }
}
