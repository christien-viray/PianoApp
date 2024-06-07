package PianoApp;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    public static void playSound(String soundFile) {
        System.out.println("Attempting to play sound: " + soundFile);
        try {
            File file = new File(soundFile);
            if (!file.exists()) {
                System.out.println("Sound file does not exist: " + soundFile);
                return;
            }
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            System.out.println("Sound played successfully: " + soundFile);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }
}
