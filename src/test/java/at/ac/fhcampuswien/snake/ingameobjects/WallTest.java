package at.ac.fhcampuswien.snake.ingameobjects;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static at.ac.fhcampuswien.snake.util.Constants.OBJECT_SIZE_MEDIUM;
import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    @ParameterizedTest
    @CsvSource({"1,2", "14,13"})
    void wallCreationHorizontal(int startingPositionX, int startingPositionY) {
        // Arrange
        List<Position> expectedSegments = List.of(
                new Position(startingPositionX, startingPositionY),
                new Position(startingPositionX + OBJECT_SIZE_MEDIUM, startingPositionY),
                new Position(startingPositionX + 2 * OBJECT_SIZE_MEDIUM, startingPositionY)
        );
        boolean isHorizontal = true;
        int length = 3;

        // Act
        Wall wall = new Wall(isHorizontal, startingPositionX, startingPositionY, length);
        List<Position> actualSegments = wall.getSegments();

        // Assert
        assertEquals(actualSegments, expectedSegments);
    }

    @ParameterizedTest
    @CsvSource({"1,2", "14,13"})
    void wallCreationVertical(int startingPositionX, int startingPositionY) {
        // Arrange
        List<Position> expectedSegments = List.of(
                new Position(startingPositionX, startingPositionY),
                new Position(startingPositionX, startingPositionY + OBJECT_SIZE_MEDIUM),
                new Position(startingPositionX, startingPositionY + 2 * OBJECT_SIZE_MEDIUM),
                new Position(startingPositionX, startingPositionY + 3 * OBJECT_SIZE_MEDIUM)
        );
        boolean isHorizontal = false;
        int length = 4;

        // Act
        Wall wall = new Wall(isHorizontal, startingPositionX, startingPositionY, length);
        List<Position> actualSegments = wall.getSegments();

        // Assert
        assertEquals(actualSegments, expectedSegments);
    }

    @ParameterizedTest
    @CsvSource({"true,1,2", "true,14,13", "false,1,2", "false,14,13"})
    void wallCreationZeroLength(boolean isHorizontal, int startingPositionX, int startingPositionY) {
        // Arrange
        String expectedMessage = "Length must be greater than 0";
        int length = 0;

        // Act & Assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Wall(isHorizontal, startingPositionX, startingPositionY, length));
        assertTrue(ex.getMessage().contains(expectedMessage));
    }

    @ParameterizedTest
    @CsvSource({"true,1,2", "true,14,13", "false,1,2", "false,14,13"})
    void wallCreationNegativeLength(boolean isHorizontal, int startingPositionX, int startingPositionY) {
        // Arrange
        String expectedMessage = "Length must be greater than 0";
        int length = -3;

        // Act & Assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Wall(isHorizontal, startingPositionX, startingPositionY, length));
        assertTrue(ex.getMessage().contains(expectedMessage));
    }

    @ParameterizedTest
    @CsvSource({"true,0,2", "true,3,0", "false,0,14", "false,15,0", })
    void wallCreationZeroStartingPosition(boolean isHorizontal, int startingPositionX, int startingPositionY) {
        // Arrange
        String expectedMessage = "Starting position must be greater than 0";
        int length = 3;

        // Act & Assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Wall(isHorizontal, startingPositionX, startingPositionY, length));
        assertTrue(ex.getMessage().contains(expectedMessage));
    }

    @ParameterizedTest
    @CsvSource({"true,1,-2", "true,-14,13", "false,1,-2", "false,-14,13"})
    void wallCreationNegativeStartingPosition(boolean isHorizontal, int startingPositionX, int startingPositionY) {
        // Arrange
        String expectedMessage = "Starting position must be greater than 0";
        int length = 3;

        // Act & Assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Wall(isHorizontal, startingPositionX, startingPositionY, length));
        assertTrue(ex.getMessage().contains(expectedMessage));
    }
}