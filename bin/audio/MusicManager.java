package audio;

import java.net.URL;
import javax.sound.sampled.*;

public class MusicManager {

    private static Clip clip;

    public static void playLoop(String path) {
        stop();

        try {
            URL url = MusicManager.class.getResource(path);
            if (url == null) {
                System.out.println("Áudio não encontrado: " + path);
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
