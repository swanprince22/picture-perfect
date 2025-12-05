import javax.sound.sampled.*;
import java.io.IOException;

public class SoundUtils {

    private static Clip bgClip;  

    public static void clickSound() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(
                SoundUtils.class.getResource("/media/sound/buttonclick.wav")
            );

            Clip clip = AudioSystem.getClip();
            clip.open(stream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-40.0f); // adjust between -80.0f (silent) and 6.0f (louder)

            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void bgMusic(int volumePercent) {  
        try {
            if (bgClip != null && bgClip.isOpen()) {
                bgClip.stop();
                bgClip.close();
            }

            AudioInputStream stream = AudioSystem.getAudioInputStream(
                SoundUtils.class.getResource("/media/sound/Spring.wav")
            );

            bgClip = AudioSystem.getClip();
            bgClip.open(stream);

            setVolume(volumePercent);   
            bgClip.loop(Clip.LOOP_CONTINUOUSLY);
            bgClip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void setVolume(int volumePercent) {
        if (bgClip == null) return;
        FloatControl gainControl = (FloatControl) bgClip.getControl(FloatControl.Type.MASTER_GAIN);

        float min = gainControl.getMinimum(); 
        float max = gainControl.getMaximum(); 
        float gain = min + (volumePercent / 100f) * (max - min);

        gainControl.setValue(gain);
    }
}
