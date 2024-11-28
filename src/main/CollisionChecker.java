package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;
    
    public CollisionChecker(GamePanel gp) {
        
        this.gp = gp;

    }

    public void checkTile (Entity entity) { //NOT PLAYER CAUSE WE ARE GOING TO USE THIS ALSO FOR NPC

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;


        switch(entity.direction) {

            case "up":

            entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
                //System.out.println("EntityLeftCol: " + entityLeftCol + "\n" + "EntityTopRow: " + entityTopRow + "\n" + "EntityRightCol: " + entityRightCol);
            }

            break;

            case "down":

            entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }

            break;

            case "left":

            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
                //System.out.println("EntityLeftCol: " + entityLeftCol + "\n" + "EntityTopRow: " + entityTopRow + "\n" + "EntityRightCol: " + entityRightCol);
            } 

            break;

            case "right":

            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
                //System.out.println("EntityLeftCol: " + entityLeftCol + "\n" + "EntityTopRow: " + entityTopRow + "\n" + "EntityRightCol: " + entityRightCol);
            } 

            break;

        }
    }

    public int checkItem (Entity entity, boolean player) {
        
        int index = 999;

        for (int i = 0; i < gp.itm.length; i++) {

            if(gp.itm[i] != null) {

                //GET ENTITY'S SOLID AREA POSITION

                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //GET THE ITEM'S SOLID AREA POSITION

                gp.itm[i].solidArea.x = gp.itm[i].worldX + gp.itm[i].solidArea.x;
                gp.itm[i].solidArea.y = gp.itm[i].worldY + gp.itm[i].solidArea.y;


                switch (entity.direction) {

                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;

                }
                   
                if(entity.solidArea.intersects(gp.itm[i].solidArea)) {
                    if(gp.itm[i].collision) { entity.collisionOn = true; }
                    if(player == true){ index = i; }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.itm[i].solidArea.x = gp.itm[i].solidAreaDefaultX;
                gp.itm[i].solidArea.y = gp.itm[i].solidAreaDefaultY;

            }

        }

        return index;
    }

    //NPC OR MONSTER

    public int checkEntity(Entity entity, Entity[] target) {

        int index = 999;

        for (int i = 0; i < target.length; i++) {

            if(target[i] != null) {

                //GET ENTITY'S SOLID AREA POSITION

                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //GET THE ITEM'S SOLID AREA POSITION

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;


                switch (entity.direction) {

                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;

                    }
        
                if(entity.solidArea.intersects(target[i].solidArea)) {
                    if(target[i] != entity){
                        entity.collisionOn = true;
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }

        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;

        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        //GET THE ITEM'S SOLID AREA POSITION
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction) {
            case "up": entity.solidArea.y -= entity.speed; break;
            case "down": entity.solidArea.y += entity.speed; break;
            case "left": entity.solidArea.x -= entity.speed; break;
            case "right": entity.solidArea.x += entity.speed; break;
        }

        if(entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }
}