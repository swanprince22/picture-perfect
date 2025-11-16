import javax.swing.*;

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

    public void rightPanelDisplay(){
        
    }
}
