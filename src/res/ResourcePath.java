package res;

import java.awt.Color;
import java.awt.Font;

import main.UtilityTool;

public class ResourcePath {


    public static Font KODE_PLAIN_FONT, KODE_BOLD_FONT, KODE_MEDIUM_FONT, KODE_REGULAR_FONT, KODE_SEMIBOLD_FONT;

    // EXTENSIONS
    public static final String fileImageExtension = ".png";
    public static final String fileAudioExtension = ".wav";
    public static final String fileFontExtension = ".ttf";

    // PATHS
    public static final String DAVID_IMAGE_PATH = "/res/sprites/David/";
    public static final String DAVID_IMAGE_RESIZE_PATH = "/res/images/";
    public static final String TILE_IMAGE_PATH = "/res/tiles/";
    public static final String NPC_IMAGE_PATH = "/res/npc/";
    public static final String MONSTER_IMAGE_PATH = "/res/monster/";
    public static final String ITEMS_IMAGE_PATH = "/res/items/";
    public static final String HEART_IMAGE_PATH = "/res/items/heart/";
    public static final String MAPS_PATH = "/res/maps/";
    public static final String SOUND_PATH = "/res/sound/";
    public static final String FONT_PATH = "/res/font/";

    // FONT
    public static final Font UI_FONT = new Font("Arial", Font.PLAIN, 40);
    public static final Font MESSAGE_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Font CONGRATULATIONS_FONT = new Font("Arial", Font.BOLD, 80);

    // IMPORTED FONTS
    public static final Font DIALOGUE_FONT_0 = UtilityTool.importFont(KODE_PLAIN_FONT, "KodeMono_wght");
    public static final Font DIALOGUE_FONT_1 = UtilityTool.importFont(KODE_REGULAR_FONT, "KodeMono-Regular");
    public static final Font DIALOGUE_FONT_2 = UtilityTool.importFont(KODE_MEDIUM_FONT, "KodeMono-Medium");
    public static final Font DIALOGUE_FONT_3 = UtilityTool.importFont(KODE_SEMIBOLD_FONT, "KodeMono-SemiBold");
    public static final Font DIALOGUE_FONT_4 = UtilityTool.importFont(KODE_BOLD_FONT, "KodeMono-Bold");

    // COLORS
    public static final Color SUBWINDOW_COLOR = new Color(0, 0, 0, 210);
    public static final Color SUBWINDOW_STROKE_COLOR = new Color(255, 255, 255);

}