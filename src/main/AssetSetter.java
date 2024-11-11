package main;

import item.ITM_Door;
import item.ITM_Boots;
import item.ITM_Chest;
import item.ITM_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;

    }

    public void setItem() {

        gp.itm[0] = new ITM_Key();
        gp.itm[0].worldX = 23 * gp.tileSize;
        gp.itm[0].worldY = 7 * gp.tileSize;

        gp.itm[1] = new ITM_Key();
        gp.itm[1].worldX = 23 * gp.tileSize;
        gp.itm[1].worldY = 40 * gp.tileSize;

        gp.itm[2] = new ITM_Key();
        gp.itm[2].worldX = 38 * gp.tileSize;
        gp.itm[2].worldY = 8 * gp.tileSize;

        gp.itm[3] = new ITM_Door();
        gp.itm[3].worldX = 10 * gp.tileSize;
        gp.itm[3].worldY = 11 * gp.tileSize;

        gp.itm[4] = new ITM_Door();
        gp.itm[4].worldX = 8 * gp.tileSize;
        gp.itm[4].worldY = 28 * gp.tileSize;

        gp.itm[5] = new ITM_Door();
        gp.itm[5].worldX = 12 * gp.tileSize;
        gp.itm[5].worldY = 22 * gp.tileSize;

        gp.itm[6] = new ITM_Chest();
        gp.itm[6].worldX = 10 * gp.tileSize;
        gp.itm[6].worldY = 7 * gp.tileSize;

        
        gp.itm[7] = new ITM_Boots();
        gp.itm[7].worldX = 37 * gp.tileSize;
        gp.itm[7].worldY = 42 * gp.tileSize;
        

    }
    
}
