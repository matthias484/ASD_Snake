package at.ac.fhcampuswien.snake.ingameobjects;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -2})
    void positionReturnsCorrectXValue(int expectedX) {
        // Arrange
        Position position = new Position(expectedX, 3);

        // Act
        int actualX = position.x();

        // Assert
        assertEquals(expectedX, actualX);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 12, -34})
    void positionReturnsCorrectYValue(int expectedY) {
        // Arrange
        Position position = new Position(5, expectedY);

        // Act
        int actualY = position.y();

        // Assert
        assertEquals(expectedY, actualY);
    }


}