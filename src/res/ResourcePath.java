package res;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class ResourcePath {

        public static Font KODE_FONT;


        public static final String fileImageExtension = ".png";
        public static final String fileAudioExtension = ".wav";
        public static final String fileFontExtension = ".ttf";
        
        public static final String DAVID_IMAGE_PATH = "/res/sprites/David/";
        public static final String TILE_IMAGE_PATH = "/res/tiles/";
        public static final String NPC_IMAGE_PATH = "/res/npc/";
        public static final String ITEMS_IMAGE_PATH = "/res/items/";
        public static final String MAPS_PATH = "/res/maps/";
        public static final String SOUND_PATH = "/res/sound/";
        public static final String FONT_PATH = "/res/font/";

        public static final Font UI_FONT = new Font("Arial", Font.PLAIN, 40);
        public static final Font MESSAGE_FONT = new Font("Arial", Font.PLAIN, 20);

        public static final Font DIALOGUE_FONT = importFont(KODE_FONT, "KodeMono_wght");
            
        
        public static final Font CONGRATULATIONS_FONT = new Font("Arial", Font.BOLD, 80);
        
        public static final Color SUBWINDOW_COLOR = new Color(0, 0, 0, 210);
        public static final Color SUBWINDOW_STROKE_COLOR = new Color (255, 255, 255);
        
        
        
        static Font importFont(Font font, String fileName) {

        InputStream is = ResourcePath.class.getResourceAsStream(FONT_PATH + fileName + fileFontExtension);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch(FontFormatException e) {

        } catch(IOException e) {
            e.printStackTrace();
        }

        return font;
    }    
}
