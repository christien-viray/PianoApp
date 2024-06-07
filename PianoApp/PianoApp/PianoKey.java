package PianoApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PianoKey {
    public static JButton createKey(Color bgColor, Color borderColor, String soundFile) {
        JButton key = new JButton();
        key.setBackground(bgColor);
        key.setOpaque(true);
        key.setBorder(BorderFactory.createLineBorder(borderColor));
        key.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                SoundPlayer.playSound(soundFile);
            }
        });
        return key;
    }
}
