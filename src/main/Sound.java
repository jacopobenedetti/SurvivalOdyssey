package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import res.ResourcePath;

import java.net.URL;

public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[30];

    String path = ResourcePath.SOUND_PATH;
    String extension = ResourcePath.fileAudioExtension;

    public Sound() {

        soundURL[0] = getClass().getResource(path + "soundtrack" + extension);
        soundURL[1] = getClass().getResource(path + "coin" + extension);
        soundURL[2] = getClass().getResource(path + "powerup" + extension);
        soundURL[3] = getClass().getResource(path + "fanfare" + extension);
        soundURL[4] = getClass().getResource(path + "unlock" + extension);


    }

    public void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch(Exception e) {

        }
    }

    public void play() {

        clip.start();

    }


    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void stop() {
        
        clip.stop();

    }
}
