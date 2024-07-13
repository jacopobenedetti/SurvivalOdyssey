package main;

import javax.swing.JFrame;
//import java.io.File;

/*
* Main Functions:
* 1. main(String[] args): This is the entry point of the application. It creates a JFrame
*    window, configures its properties, and starts the game.
*/

public class Main {
    public static void main (String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Beezie's Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // 

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

        // String filePath = "/src/res/tiles/grass.png";  // Sostituisci con il percorso reale del tuo file

        // File file = new File(filePath);

        // if (file.exists()) {
        //     System.out.println("Il file esiste.");
        // } else {
        //     System.out.println("Il file non esiste.");
        // }
    }
}
