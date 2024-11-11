package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import main.GamePanel;
import main.UtilityTool;
import res.ResourcePath;

public class TileManager {
    

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum [][];

    String tilePath = ResourcePath.TILE_IMAGE_PATH;
    String mapsPath = ResourcePath.MAPS_PATH;
    String imageExtension = ResourcePath.fileImageExtension;
    


    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[15];

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap(mapsPath + "world01.txt");

    }

    public void getTileImage() {

        try {

            setup(0, "earth", false);
            setup(1, "grass", false);
            setup(2, "mars", false);
            setup(3, "sand", false);
            setup(4, "wall", true);
            setup(5, "water", false);
            setup(6, "tree", true);
            setup(7, "middleGrass_down", false);
            setup(8, "middleGrass_up", false);
            setup(9, "middleGrass_left", false);
            setup(10, "middleGrass_right", false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setup(int index, String imagePath, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {

            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(tilePath + imagePath + imageExtension));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while(col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
            
                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;

                }
            }
            br.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw (Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(
                worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
            ) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;


            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }

        }

    }
}
