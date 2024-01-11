package at.ac.fhcampuswien.snake.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {

    @Test
    void initialDirection_isRight() {
        assertEquals(Constants.Direction.RIGHT, Constants.INITIAL_DIRECTION);
    }

    @ParameterizedTest
    @CsvSource({"LEFT", "RIGHT", "UP", "DOWN"})
    void direction(String expected) {
        assertNotNull(Constants.Direction.valueOf(expected));
    }

    @ParameterizedTest
    @CsvSource({"POINTS", "CHARACTER", "OBSTACLE"})
    void type(String expected) {
        assertNotNull(Constants.Type.valueOf(expected));
    }

    @ParameterizedTest
    @CsvSource({"EASY", "MEDIUM", "HARD"})
    void difficulty(String expected) {
        assertNotNull(Constants.Difficulty.valueOf(expected));
    }

    @ParameterizedTest
    @CsvSource({"EASY,300", "MEDIUM,200", "HARD,150"})
    void difficultyGetRefreshTime(Constants.Difficulty difficulty, int expectedRefreshTime) {
        // Arrange & Act
        int actualRefreshTime = difficulty.getRefreshTime();

        // Assert
        assertEquals(expectedRefreshTime, actualRefreshTime);
    }
}