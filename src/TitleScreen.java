import java.awt.*;
import javax.swing.*;

public class TitleScreen extends Design {
    JLabel start;
    Card mainFrame;
    Utilities utilities;
    Design design;

    public TitleScreen(Card mainFrame){
        super("/media/img/background.png");
        this.mainFrame = mainFrame;

        utilities = new Utilities(mainFrame);
        utilities.displayWhitePanel();
        this.add(utilities);

        display();
        
        this.setVisible(true);
    }

    public void display(){
        design = new Design("/media/img/title.png");
        utilities.getWhitePanel().add(design, BorderLayout.CENTER);
        
        start = new JLabel("Start", SwingConstants.CENTER);
        start.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        utilities.getWhitePanel().add(start, BorderLayout.SOUTH);
       
        ButtonClicks.mouseClicked(start, "Menu", mainFrame);
    }
}
