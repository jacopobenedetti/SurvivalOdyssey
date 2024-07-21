package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class TileManager {
    

    GamePanel gp;
    Tile[] tile;
    int mapTileNum [][];


    String TILE_PATH = "/res/tiles/";


    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[15];

        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/res/maps/map01.txt");

    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream(TILE_PATH + "earth.png"));
            
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream(TILE_PATH + "grass.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream(TILE_PATH + "mars.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream(TILE_PATH + "sand.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream(TILE_PATH + "wall.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream(TILE_PATH + "water.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream(TILE_PATH + "tree.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream(TILE_PATH + "middleGrass_down.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream(TILE_PATH + "middleGrass_up.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream(TILE_PATH + "middleGrass_left.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream(TILE_PATH + "middleGrass_right.png"));



        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public void loadMap(String filePath) {

        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = br.readLine();

                while(col < gp.maxScreenCol) {

                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
            
                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gp.maxScreenCol) {
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

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }

        }

    }
}