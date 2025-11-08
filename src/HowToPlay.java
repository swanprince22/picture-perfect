import java.awt.*;
import javax.swing.*;

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
        instructions.setText("<html><div style='text-align: center;'>"
                + "Rearrange the tiles to form the complete picture.<br>"
                + "Click or tap a tile next to the empty space to move it.<br>"
                + "Keep sliding the tiles until the image is complete.<br>"
                + "</div></html>");
        
         utilities.getRightPanel().add(instructions, BorderLayout.CENTER);       
    }
}

