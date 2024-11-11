package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ITM_Boots extends SuperItem {

        public ITM_Boots() {
        name = "Boots";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path + name + imageExtension));

            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

