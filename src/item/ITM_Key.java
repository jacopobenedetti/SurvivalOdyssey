package item;

import java.io.IOException;
import javax.imageio.ImageIO;

public class ITM_Key extends SuperItem {
    
    public ITM_Key() {
        name = "Key";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream(filePath + name + fileExtension));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
