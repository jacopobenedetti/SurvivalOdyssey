package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/*
* Main Functions:
* 1. Constructor (`Sound`): Initializes the array of sound URLs and loads the sound files.
* 2. setFile(int i): Sets up the audio input stream and prepares the clip for playback.
* 3. play(): Starts playing the loaded audio clip.
* 4. loop(): Loops the loaded audio clip continuously.
* 5. stop(): Stops the currently playing audio clip.
*/


public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];
    public final String SOUND_PATH = "/res/sound/";
    
    public Sound() {
        
        soundURL[0] = getClass().getResource(SOUND_PATH + "BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource(SOUND_PATH + "coin.wav");
        soundURL[2] = getClass().getResource(SOUND_PATH + "powerup.wav");
        soundURL[3] = getClass().getResource(SOUND_PATH + "unlock.wav");
        soundURL[4] = getClass().getResource(SOUND_PATH + "fanfare.wav");

    }

    public void setFile(int i) {
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        
        } catch (Exception e) {
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
