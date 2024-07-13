package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
//import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//import java.io.File;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;


public class TileManager {
    

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    //res PATH
    public final String IMAGE_TILES_PATH = "/res/tiles/";
    public final String MAP_PATH = "/res/maps/";
    
    public TileManager (GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10]; //Tiles, like grass tile, water tile, ecc...
        mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];


        getTileImage();
        loadMap(MAP_PATH + "world01.txt");
    }

    public void getTileImage() {

            setup(0, "grass", false);
            setup(1, "wall", true);
            setup(2, "water", true);
            setup(3, "earth", false);
            setup(4, "tree", true);
            setup(5, "sand", false);

    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {

            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(IMAGE_TILES_PATH + imageName + ".png"));
            tile[index].image = uTool.scaledimage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {

            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            int col = 0; 

            while(col < gp.maxWorldCol && row < gp.maxWorldCol) {
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int number = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = number;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0; 
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow ) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            boolean cameraSize = worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY  + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY;

            if(cameraSize) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    
            }


            // System.out.print(screenY);
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
             worldRow++;
            }
        }
    }
}