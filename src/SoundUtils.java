import javax.sound.sampled.*;
import java.io.IOException;

public class SoundUtils {
    public void clickSound(){
    try {
           AudioInputStream bgStream = AudioSystem.getAudioInputStream(getClass().getResource("/media/sound/buttonclick.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(bgStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void bgMusic(){
        try{
            AudioInputStream bgStream = AudioSystem.getAudioInputStream(getClass().getResource("/media/sound/buttonclick.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(bgStream);
            clip.start();
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
