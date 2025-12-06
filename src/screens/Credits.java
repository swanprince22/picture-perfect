package screens;
import javax.swing.*;

import utils.Card;
import utils.Design;
import utils.Utilities;

import java.awt.*;

public class Credits extends Design {
    JPanel leftPanel, rightPanel;
    Card mainFrame; 
    JPanel centerPanel;
    Utilities utilities; 

    public Credits(Card mainFrame){
        super("/media/img/background.png");
        this.mainFrame = mainFrame;

        utilities = new Utilities(mainFrame);
        utilities.displayWhitePanel();
        utilities.displayOptions();
        rightPanelDisplay();

        this.add(utilities);
        this.setVisible(true);
    }

    public void rightPanelDisplay(){
        JLabel credits = new JLabel();
        credits.setForeground(Color.PINK);
        credits.setHorizontalAlignment(SwingConstants.CENTER);

        credits.setText("<html><div style='text-align:center;'>"
                + "<p><b>Music:</b> \"Spring · Minecraft\" by Mike Fortu & Carlos Ramirez</p>"
                + "<p>℗ 2025 Microsoft Studios Music</p>"
                + "<br>"
                + "<p><b>Image: © Peanuts Worldwide LLC"
                + "</div></html>");

            utilities.getRightPanel().add(credits, BorderLayout.CENTER);
    }
}
