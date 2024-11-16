package main;

import entity.NPC_Robot;
import item.ITM_Door;

/*
import item.ITM_Door;
import item.ITM_Boots;
import item.ITM_Chest;
import item.ITM_Key;
*/

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;

    }

    public void setItem() {

        gp.itm[0] = new ITM_Door(gp);
        gp.itm[0].worldX = gp.tileSize * 21;
        gp.itm[0].worldY = gp.tileSize * 22;

        gp.itm[1] = new ITM_Door(gp);
        gp.itm[1].worldX = gp.tileSize * 23;
        gp.itm[1].worldY = gp.tileSize * 25;

    }

    public void setNPC() {

        gp.npc[0] = new NPC_Robot(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;

        gp.npc[1] = new NPC_Robot(gp);
        gp.npc[1].worldX = gp.tileSize * 11;
        gp.npc[1].worldY = gp.tileSize * 21;

    }
    
}
