package at.ac.fhcampuswien.snake.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class SoundFXTest {

    @Test
    void playIntroSound() {
        // Arrange & Act
        Throwable throwable = catchThrowable(SoundFX::playIntroSound);

        // Assert
        assertThat(throwable).doesNotThrowAnyException();
    }

    @Test
    void playEatingSound() {
        // Arrange & Act
        Throwable throwable = catchThrowable(SoundFX::playEatingSound);

        // Assert
        assertThat(throwable).doesNotThrowAnyException();
    }

    @Test
    void playBonusPointSound() {
        // Arrange & Act
        Throwable throwable = catchThrowable(SoundFX::playBonusPointSound);

        // Assert
        assertThat(throwable).doesNotThrowAnyException();
    }

    @Test
    void playGameOverSound() {
        // Arrange & Act
        Throwable throwable = catchThrowable(SoundFX::playGameOverSound);

        // Assert
        assertThat(throwable).doesNotThrowAnyException();
    }
}