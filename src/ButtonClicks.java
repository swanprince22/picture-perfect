import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class ButtonClicks {
    static SoundUtils sound = new SoundUtils();
    public static void mouseClicked(JLabel label, String targetPanel, Card mainFrame) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.showPanel(targetPanel);
                sound.clickSound();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(Color.PINK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(Color.BLACK);
            }
        });
    }
}
