import javax.sound.sampled.*;
import java.io.File;

/**
 * Reproduz sons de forma assincrona com o jogo.
 */

public class SoundPlayer {
    private static final String SFX_DIR = "sfx/";

    /**
     * Toca o som passado, dez de que esteja na pasta "SFX_DIR".
     * @param sfxFile -> Nome do audio com extensao
     */
    
    public static void play(String sfxFile) {
        new Thread(() -> {
            try {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(SFX_DIR + sfxFile));
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();

                while (clip.isRunning()) {
                    Thread.sleep(100);
                }

                clip.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Tocar som padrao para erro.
     */
    
    public static void playError() {
        String sfxFile = "sfx-error-8-bit.wav";

        SoundPlayer.play(sfxFile);
    }
}
