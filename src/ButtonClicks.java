import java.awt.*;
import java.awt.event.*;
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

    public static void playButtonClicked(JLabel label, Card mainFrame) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.showPanel("Play");
                sound.clickSound();
                mainFrame.getPlayPanel().startGame();
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
