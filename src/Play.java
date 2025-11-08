import java.awt.*;
import javax.swing.*;

public class Play extends Design {
    JLabel start;
    Card mainFrame;
    Utilities utilities;
    Design puzzleImage;
    Design one, two, three, four, five, six, seven, eight, nine;

    public Play(Card mainFrame){
        super("/media/img/background.png");
        this.mainFrame = mainFrame;

        utilities = new Utilities(mainFrame);
        utilities.displayWhitePanel();
        this.add(utilities);

        displayGrid();
        
        this.setVisible(true);
    }

    public void displayGrid(){
        one = new Design("/media/img/1.png");
        two = new Design("/media/img/2.png");
        three = new Design("/media/img/3.png");
        four = new Design("/media/img/4.png");
        five = new Design("/media/img/5.png");
        six = new Design("/media/img/6.png");
        seven = new Design("/media/img/7.png");
        eight = new Design("/media/img/8.png");
        
        JPanel nine = new JPanel();
        nine.setBackground(Color.WHITE);

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));

        gridPanel.add(one);
        gridPanel.add(two);
        gridPanel.add(three);
        gridPanel.add(four);
        gridPanel.add(five);
        gridPanel.add(six);
        gridPanel.add(seven);
        gridPanel.add(eight);
        gridPanel.add(nine);

        utilities.getWhitePanel().add(gridPanel, BorderLayout.CENTER);

    }
}
