package at.ac.fhcampuswien.snake.ingameobjects;

import at.ac.fhcampuswien.snake.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    @ParameterizedTest
    @CsvSource({"UP", "DOWN", "LEFT", "RIGHT"})
    void snakeInitialDirection(Constants.Direction expectedDirection) {
        // Arrange & Act
        Snake snake = new Snake(3, expectedDirection);
        Constants.Direction actualDirection = snake.getDirection();

        // Assert
        assertEquals(expectedDirection, actualDirection);
    }

    @Test
    void snakeInitialLength() {
        // Arrange & Act
        int expectedLength = 3;
        Snake snake = new Snake(3, Constants.Direction.RIGHT);
        int actualLength = snake.getSegments().size();

        // Assert
        assertEquals(expectedLength, actualLength);
    }

    @Test
    void snakeInitialAlive() {
        // Arrange & Act
        Snake snake = new Snake(3, Constants.Direction.RIGHT);
        boolean actualAlive = snake.isAlive();

        // Assert
        assertTrue(actualAlive);
    }

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void snakeEatsRegularOrSpecialFood(boolean isSpecialFood) {
        // Arrange
        int expectedLength = 4;
        Snake snake = new Snake(3, Constants.Direction.RIGHT);
        Wall wall = new Wall(true, 1, 1, 3);
        Food food = new Food(snake, wall, null, isSpecialFood, "");

        // Act
        snake.eats(food);
        int actualLength = snake.getSegments().size();

        // Assert
        assertEquals(expectedLength, actualLength);
    }

    @Test
    void snakeCheckForNoCollisionsWithWall() {
        // Arrange
        Snake snake = new Snake(3, Constants.Direction.RIGHT);
        Wall wall = new Wall(true, 1, 1, 3);

        // Act
        for (int i = 0; i < 5; i++) {
            snake.updateSnakePosition();
        }
        snake.checkForCollisions(wall);

        // Assert
        assertTrue(snake.isAlive());
    }

    @Test
    void snakeCheckForCollisionsWithGameBorder() {
        // Arrange
        Snake snake = new Snake(3, Constants.Direction.RIGHT);
        Wall wall = new Wall(true, 1, 1, 3);

        // Act
        for (int i = 0; i < 10; i++) {
            snake.updateSnakePosition();
        }
        snake.checkForCollisions(wall);

        // Assert
        assertFalse(snake.isAlive());
    }

    @Test
    void snakeCheckForNoCollisionsWithItself() {
        // Arrange
        Snake snake = new Snake(5, Constants.Direction.RIGHT);
        Wall wall = new Wall(true, 1, 1, 3);

        // Act
        snake.updateSnakePosition();
        snake.checkForCollisions(wall);
        snake.setDirection(Constants.Direction.DOWN);
        snake.updateSnakePosition();
        snake.checkForCollisions(wall);
        snake.setDirection(Constants.Direction.LEFT);
        snake.updateSnakePosition();
        snake.checkForCollisions(wall);

        // Assert
        assertTrue(snake.isAlive());
    }

    @Test
    void snakeCheckForCollisionsWithItself() {
        // Arrange
        Snake snake = new Snake(5, Constants.Direction.RIGHT);
        Wall wall = new Wall(true, 1, 1, 3);

        // Act
        snake.updateSnakePosition();
        snake.checkForCollisions(wall);
        snake.setDirection(Constants.Direction.DOWN);
        snake.updateSnakePosition();
        snake.checkForCollisions(wall);
        snake.setDirection(Constants.Direction.LEFT);
        snake.updateSnakePosition();
        snake.checkForCollisions(wall);
        snake.setDirection(Constants.Direction.UP);
        snake.updateSnakePosition();
        snake.checkForCollisions(wall);

        // Assert
        assertFalse(snake.isAlive());
    }

    @Test
    void setPositionUpdatedTrue() {
        // Arrange
        Snake snake = new Snake(3, Constants.Direction.RIGHT);

        // Act
        snake.setPositionUpdated(true);
        boolean actual = snake.isPositionUpdated();

        // Assert
        assertTrue(actual);
    }

    @Test
    void setPositionUpdatedFalse() {
        // Arrange
        Snake snake = new Snake(3, Constants.Direction.RIGHT);

        // Act
        snake.setPositionUpdated(false);
        boolean actual = snake.isPositionUpdated();

        // Assert
        assertFalse(actual);
    }
}