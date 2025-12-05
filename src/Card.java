import java.awt.*;
import javax.swing.*;

public class Card extends JFrame{
    private TitleScreen title;
    private Menu menu;
    private HowToPlay howToPlay;
    private CardLayout cardLayout; 
    private Settings settings;
    private Credits credits;
    private Play play;
    private JPanel container;

    protected static final int width = 500;
    protected static final int height = 600;
    protected static final Dimension SCREEN_SIZE = new Dimension(width, height);
    

    public Card(){
        this.cardLayout = new CardLayout();
        this.container = new JPanel(cardLayout);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setPreferredSize(SCREEN_SIZE);
        this.setResizable(false);

        credits = new Credits(this);
        play = new Play(this);
        settings = new Settings(this);
        title = new TitleScreen(this);
        menu = new Menu(this);
        howToPlay = new HowToPlay(this);

        container.add(menu, "Menu");
        container.add(title, "Title");
        container.add(howToPlay, "How To Play");
        container.add(credits, "Credits");
        container.add(settings, "Settings");
        container.add(play, "Play");

        cardLayout.show(container, "Title");

        SoundUtils.bgMusic(55);

        this.add(container);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void showPanel(String name){
        cardLayout.show(container, name);
    }

     public Play getPlayPanel() {
        return play;
    }
}
