package main;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile

    // Tile manager requires this size, so i've to put this "public"
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768px
    public final int screenHeight = tileSize * maxScreenRow; // 576px

    // World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeigth = tileSize * maxWorldRow;

    //Game state

    /*
    When you play a game usually it has various game situations such as the title screen, the main gameplay screen or in-menu screen and other stuff like that and depending
    on the situation the program has to display different thing on the screen and often recieve different key input
    For example you can swing your sword by pressing Enter in gameplay state but maybe Enter works as a confirmation key in menu screen. 
    So we handle this telling our program what kind of gamestate we are in right now. To do this we assigned 1 to this play state and 2 to pause state. 
    */

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    // FPS

    int FPS = 60;

    // System
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound(); //one for music and one for soundeffects, so it can process both at same time
    Sound se = new Sound();
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    // Entity objects
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; // preparing 10 slots for objects doesn't mean that we have to use
                                                    // at least 10 objects; furthermore, it means that we can use up to
                                                    // 10 objects at the same time!

    public void setupGame() {

        assetSetter.setObject();
        playMusic(0);
        stopMusic();
        gameState = playState;

    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override

    public void run() { // create a gameloop

        double drawInterval = 1000000000 / FPS; // one billion nanosecond, 0.016666 seconds
        // double nextDrawTime = System.nanoTime() + drawInterval;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                // System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update() { // update player's information

        if (gameState == playState) {
            player.update();
        }
        if (gameState == pauseState) {
            // nothing
        }
    }

    public void paintComponent(Graphics g) { // redraw gamePanel based on new information of update method
        super.paintComponent(g); // used super cause Graphics is a sublcass of JPanel

        Graphics2D g2 = (Graphics2D) g;

        //DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime){
            drawStart = System.nanoTime();
        }
        

        // Tiles
        tileM.draw(g2);

        // Objects
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // Player
        player.draw(g2);

        //UI
        ui.draw(g2);

        if(keyH.checkDrawTime){
            long drawEnd = System.nanoTime();
            long passed = drawEnd + drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw time: " + passed, 10, 100);
            System.out.println("Time: " + passed);
        }


        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }

}
