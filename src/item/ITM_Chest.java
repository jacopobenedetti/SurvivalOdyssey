package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ITM_Chest extends SuperItem {

    public ITM_Chest() {
        
        name = "Chest";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path + name + imageExtension));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    
}
