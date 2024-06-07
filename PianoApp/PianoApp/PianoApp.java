package PianoApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PianoApp extends JFrame {

    private static final int numWhiteKeys = 22;
    private static final int numBlackKeys = 15;
    private static final String[] whiteKeyNotes = {
        "C", "D", "E", "F", "G", "A", "B"
    };
    private static final String[] blackKeyNotes = {
        "C#", "D#","F#", "G#", "A#"
    };
    private static final int[] black_Key_Positions ={
        1,2,4,5,6
    };

    public PianoApp (){
        setTitle("Digital Piano App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel pianoPanel = new JPanel();
        pianoPanel.setLayout(null);
        pianoPanel.setPreferredSize(new Dimension (1000, 300));

        createWhiteKeys(pianoPanel);
        createBlackKeys(pianoPanel);

        add(pianoPanel, BorderLayout.CENTER);
        setSize(1000, 400); 
        setResizable(false);

        addWindowFocusListener(new WindowAdapter(){
            @Override
            public void windowGainedFocus(WindowEvent e) {
                bringBlackKeysToFront(pianoPanel);
            }
        });
    }

    private void createWhiteKeys(JPanel panel){

        int whiteKeyPosition = 10;
        int keyWidth = 30;
        int keyHeight = 200;
        int keySpacing = 35;

        for (int i = 0; i < numWhiteKeys; i++) {
            String note;
            int octave;
            if (i==0){
                note = "B";
                octave = 2;
            }
            else {
                note = whiteKeyNotes[(i-1) % 7];
                octave = (i-1) / 7 + 3;
            }
            JButton key = PianoKey.createKey(Color.WHITE, Color.BLACK, "PianoApp/sounds/piano_" + note + octave + ".wav");
            key.setBounds(whiteKeyPosition,50,keyWidth, keyHeight);
            
            key.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    bringBlackKeysToFront(panel);
                }

                @Override
                public void mouseReleased (MouseEvent e) {
                    bringBlackKeysToFront(panel);
                }
            });
            panel.add(key);
            whiteKeyPosition += keySpacing;
        }
    }
    private void createBlackKeys (JPanel panel){
        int keyWidth = 20;
        int keyHeight = 120;
        int whiteKeySpacing = 35;
        int blackKeyOffset = (whiteKeySpacing - keyWidth) / 2;

        int[] blackKeyPattern = {
            1, 2, 4, 5, 6
        };
        int whiteKeyIndex = 0;
        int blackKeyIndex = 0;
        int octave = 3;

        for(int i =0; i< numWhiteKeys; i++) {

            if(i % 7 == 0 && i!=0) {
                octave++;
            }

            if (i % 7 == blackKeyPattern[0] || i % 7 == blackKeyPattern[1] ||
                i % 7 ==blackKeyPattern[2] || i % 7 ==blackKeyPattern[3] ||
                i % 7 ==blackKeyPattern[4]) {

                String note = blackKeyNotes[blackKeyIndex % blackKeyPattern.length];
                JButton key = PianoKey.createKey(Color.BLACK,Color.WHITE,"PianoApp/sounds/piano_" + note + octave + ".wav");
                
                int x = 10 + (i * whiteKeySpacing) + whiteKeySpacing - (keyWidth / 2);
                int y = 50;
                key.setBounds(x, y,keyWidth, keyHeight);
                key.setMargin(new Insets(0,0,0,0));

                key.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        bringBlackKeysToFront(panel);
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        bringBlackKeysToFront(panel);
                    }
                });
                panel.add(key);                
                panel.setComponentZOrder(key,0);
                blackKeyIndex++;
            }
        }
        panel.repaint();
    }
        
        private void bringBlackKeysToFront (JPanel panel) {
            for (Component component : panel.getComponents()){
                if (component instanceof JButton){
                    JButton key = (JButton) component;
                    if (key.getBackground() == Color.BLACK) {
                        panel.setComponentZOrder(key, 0);
                    }
                }
            }
            panel.repaint();
        }
    


    public static void main(String[]args) {
        SwingUtilities.invokeLater(() -> new PianoApp().setVisible(true));
        };
}

    
