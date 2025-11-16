import javax.swing.*;
import java.awt.*;

public class Utilities extends JPanel {
    protected JPanel whitePanel;
    protected JPanel leftPanel, rightPanel;
    protected JLabel play, howtoplay, settings, credits;
    Card mainFrame;
    Play playGame;

    public Utilities(Card mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setOpaque(false);
    }

    public void displayWhitePanel() {
        whitePanel = new JPanel(new BorderLayout());
        whitePanel.setPreferredSize(new Dimension(400, 400));
        whitePanel.setBackground(Color.WHITE);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        wrapper.add(whitePanel, BorderLayout.CENTER);
        this.add(wrapper, BorderLayout.CENTER);
    }

    public JPanel getWhitePanel() {
        return whitePanel;
    }

    public void displayOptions() {
        leftPanel = new JPanel(new GridBagLayout());
        rightPanel = new JPanel(new BorderLayout());

        leftPanel.setOpaque(false);
        rightPanel.setOpaque(false);

        leftPanel.setPreferredSize(new Dimension(150, 400));
        rightPanel.setPreferredSize(new Dimension(250, 400));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(15, 0, 0, 0);

        play = new JLabel("Play");
        gbc.gridy = 0;
        leftPanel.add(play, gbc);

        howtoplay = new JLabel("How to Play");
        gbc.gridy = 1;
        leftPanel.add(howtoplay, gbc);

        settings = new JLabel("Settings");
        gbc.gridy = 2;
        leftPanel.add(settings, gbc);

        credits = new JLabel("Credits");
        gbc.gridy = 3;
        leftPanel.add(credits, gbc);

        whitePanel.add(leftPanel, BorderLayout.WEST);
        whitePanel.add(rightPanel, BorderLayout.CENTER);


        ButtonClicks.mouseClicked(settings, "Settings", mainFrame);
        ButtonClicks.mouseClicked(howtoplay, "How To Play", mainFrame);
        ButtonClicks.mouseClicked(credits, "Credits", mainFrame);
        ButtonClicks.playButtonClicked(play, mainFrame);
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }
}
