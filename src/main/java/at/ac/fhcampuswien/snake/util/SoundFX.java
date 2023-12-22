package at.ac.fhcampuswien.snake.util;

import javafx.scene.media.AudioClip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.Objects;

/**
 * This class encapsulates the sound effects used in the game.
 * It provides methods to play each sound effect.
 */
public class SoundFX {

    private static final Logger LOG = LoggerFactory.getLogger(SoundFX.class);

    private static final String INTRO_SOUND = "/sounds/game-intro.wav";
    private static final String BONUS_POINT_SOUND = "/sounds/bonus-point.wav";
    private static final String EATING_SOUND = "/sounds/eat.mp3";
    private static final String GAME_OVER_SOUND = "/sounds/game-over.wav";

    /**
     * Private constructor to hide the implicit public one.
     */
    private SoundFX() {

    }

    public static void playIntroSound() {
        playSoundFx(INTRO_SOUND);
    }

    public static void playEatingSound() {
        playSoundFx(EATING_SOUND);
    }

    public static void playBonusPointSound() {
        playSoundFx(BONUS_POINT_SOUND);
    }

    public static void playGameOverSound() {
        playSoundFx(GAME_OVER_SOUND);
    }

    /**
     * This method plays the sound effect specified by the resource path.
     *
     * @param resourcePath The path to the audio resource.
     */
    private static void playSoundFx(String resourcePath) {
        try {
            AudioClip audioClip = createAudioClip(resourcePath);
            audioClip.play();
        } catch (URISyntaxException ex) {
            LOG.error("Error while playing sound", ex);
        }
    }

    /**
     * Creates an AudioClip from the provided resource path.
     *
     * @param resourcePath The path to the audio resource.
     * @return An AudioClip object.
     * @throws URISyntaxException If the resource path is not a valid URI.
     */
    private static AudioClip createAudioClip(String resourcePath) throws URISyntaxException {
        String path = Objects.requireNonNull(SoundFX.class.getResource(resourcePath)).toURI().toString();
        return new AudioClip(path);
    }
}
