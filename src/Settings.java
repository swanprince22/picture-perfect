import javax.swing.*;
import java.awt.*;

public class Settings extends Design {
    JPanel leftPanel, rightPanel;
    Card mainFrame; 
    JPanel centerPanel;
    Utilities utilities; 

    public Settings(Card mainFrame){
        super("/media/img/background.png");
        this.mainFrame = mainFrame;

        utilities = new Utilities(mainFrame);
        utilities.displayWhitePanel();
        utilities.displayOptions();
        rightPanelDisplay();

        this.add(utilities);
        this.setVisible(true);
    }

        public void rightPanelDisplay() {

        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel sliderTitle = new JLabel("Music Volume");
        sliderTitle.setForeground(Color.PINK);

        JSlider slider = new JSlider(0, 100, 50) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };

        slider.setOpaque(false);
        slider.setPreferredSize(new Dimension(220, 30));
        slider.setUI(new javax.swing.plaf.basic.BasicSliderUI(slider) {

            @Override
            public void paintTrack(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int trackHeight = 10;
                int y = trackRect.y + (trackRect.height / 2) - (trackHeight / 2);

                g2.setColor(Color.PINK);
                g2.fillRoundRect(trackRect.x, y, trackRect.width, trackHeight, 20, 20);
            }

            @Override
            public void paintThumb(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int r = 18;

                int x = thumbRect.x + (thumbRect.width / 2) - (r / 2);
                int y = thumbRect.y + (thumbRect.height / 2) - (r / 2);

                g2.setColor(Color.PINK);
                g2.fillOval(x, y, r, r);

                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Arial", Font.BOLD, 12));
                g2.drawString("♥", x + 4, y + 13);
                g2.setColor(new Color(255, 170, 200, 100));
                g2.fillOval(x - 4, y - 4, r + 8, r + 8);

            }
        });

        JLabel valueLabel = new JLabel("60%");
        valueLabel.setForeground(Color.WHITE);

        slider.addChangeListener(e -> {
            int value = slider.getValue();
            valueLabel.setText(value + "%");

        
            SoundUtils.setVolume(value);
        });


        JPanel sliderBox = new JPanel();
        sliderBox.setOpaque(false);
        sliderBox.setLayout(new BoxLayout(sliderBox, BoxLayout.Y_AXIS));

        sliderTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        slider.setAlignmentX(Component.CENTER_ALIGNMENT);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        sliderBox.add(sliderTitle);
        sliderBox.add(Box.createVerticalStrut(15));
        sliderBox.add(slider);
        sliderBox.add(Box.createVerticalStrut(5));
        sliderBox.add(valueLabel);

        rightPanel.add(sliderBox, gbc);

        utilities.getWhitePanel().add(rightPanel, BorderLayout.CENTER);
    }
}
