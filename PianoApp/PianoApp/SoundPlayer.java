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

            AudioFormat baseFormat = audioStream.getFormat();
            AudioFormat decodedFormat = new AudioFormat (
                AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(),
                16,
                baseFormat.getChannels(),
                baseFormat.getChannels()*2,
                baseFormat.getSampleRate(),
                false
            );

            AudioInputStream decodedAudioStream = AudioSystem.getAudioInputStream(decodedFormat,audioStream);
            
            Clip clip = AudioSystem.getClip();
            clip.open(decodedAudioStream);
            clip.addLineListener (event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            System.out.println("Sound file length: " + clip.getMicrosecondLength() + " microseconds");
            clip.start();
            System.out.println("Sound played successfully: " + soundFile);
        }
        catch (UnsupportedAudioFileException e) {
            System.err.println("Unsupported audio file: " + soundFile);
            e.printStackTrace();
        }
        catch (IOException e){
            System.err.println("I/O error while playing sound: " + soundFile);
            e.printStackTrace();
        }
        catch (LineUnavailableException e) {
            System.err.println("Audio line unavailable: " + soundFile);
            e.printStackTrace();
        }
    }
}
