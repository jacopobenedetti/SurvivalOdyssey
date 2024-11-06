package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ITM_Door extends SuperItem {

    public ITM_Door() {

        name = "Door";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream(filePath + name + fileExtension));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
