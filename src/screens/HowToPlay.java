package screens;
import java.awt.*;
import javax.swing.*;

import utils.Card;
import utils.Design;
import utils.Utilities;

public class HowToPlay extends Design {
    JPanel leftPanel, rightPanel;
    Card mainFrame; 
    JPanel centerPanel;
    Utilities utilities; 
    JLabel instructions;

    public HowToPlay(Card mainFrame){
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
        instructions = new JLabel();
        instructions.setForeground(Color.PINK);

        instructions.setText("<html><div style='text-align:center;'>"
        + "Click a tile next to the empty space to move it!<br>"
        + "Slide the pieces until the picture is complete 💫<br>"
        + "<br>"
        + "Need help? Tap <b>Solve</b> and watch the magic happen ✨<br>"
        + "</div></html>");

        
         utilities.getRightPanel().add(instructions, BorderLayout.CENTER);       
    }
}

