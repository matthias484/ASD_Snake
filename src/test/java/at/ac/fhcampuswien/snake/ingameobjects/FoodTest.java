package at.ac.fhcampuswien.snake.ingameobjects;

import at.ac.fhcampuswien.snake.util.Constants;
import at.ac.fhcampuswien.snake.util.StateManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void constructorRegularFoodCreationEasy(boolean isHorizontal) {
        // Arrange
        StateManager.setDifficulty(Constants.Difficulty.EASY);
        Snake snake = new Snake(4, Constants.Direction.RIGHT);
        Wall wall = new Wall(isHorizontal, 1, 1, 3);
        boolean isSpecialFood = false;

        // Act
        Food food = new Food(snake, wall, null, isSpecialFood, "");

        // Assert
        assertFalse(food.isSpecialFood());
        assertNotEquals(0, food.getSpecialFoodTimeToLive());
        assertNotNull(food.getFoodType());
        assertNotNull(food.getLocation());
        assertTrue(food.getScoreValue() > 0);
    }

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void constructorRegularFoodCreationMedium(boolean isHorizontal) {
        // Arrange
        StateManager.setDifficulty(Constants.Difficulty.MEDIUM);
        Snake snake = new Snake(4, Constants.Direction.RIGHT);
        Wall wall = new Wall(isHorizontal, 1, 1, 3);
        boolean isSpecialFood = false;

        // Act
        Food food = new Food(snake, wall, null, isSpecialFood, "");

        // Assert
        assertFalse(food.isSpecialFood());
        assertNotEquals(0, food.getSpecialFoodTimeToLive());
        assertNotNull(food.getFoodType());
        assertNotNull(food.getLocation());
        assertTrue(food.getScoreValue() > 0);
    }

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void constructorRegularFoodCreationHard(boolean isHorizontal) {
        // Arrange
        StateManager.setDifficulty(Constants.Difficulty.HARD);
        Snake snake = new Snake(4, Constants.Direction.RIGHT);
        Wall wall = new Wall(isHorizontal, 1, 1, 3);
        boolean isSpecialFood = false;

        // Act
        Food food = new Food(snake, wall, null, isSpecialFood, "");

        // Assert
        assertFalse(food.isSpecialFood());
        assertNotEquals(0, food.getSpecialFoodTimeToLive());
        assertNotNull(food.getFoodType());
        assertNotNull(food.getLocation());
        assertTrue(food.getScoreValue() > 0);
    }

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void constructorSpecialFoodCreationEasy(boolean isHorizontal) {
        // Arrange
        StateManager.setDifficulty(Constants.Difficulty.EASY);
        Snake snake = new Snake(4, Constants.Direction.RIGHT);
        Wall wall = new Wall(isHorizontal, 1, 1, 3);
        boolean isSpecialFood = true;

        // Act
        Food food = new Food(snake, wall, null, isSpecialFood, "");

        // Assert
        assertTrue(food.isSpecialFood());
        assertNotEquals(0, food.getSpecialFoodTimeToLive());
        assertNotNull(food.getFoodType());
        assertNotNull(food.getLocation());
        assertTrue(food.getScoreValue() > 0);
    }

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void constructorSpecialFoodCreationMedium(boolean isHorizontal) {
        // Arrange
        StateManager.setDifficulty(Constants.Difficulty.MEDIUM);
        Snake snake = new Snake(4, Constants.Direction.RIGHT);
        Wall wall = new Wall(isHorizontal, 1, 1, 3);
        boolean isSpecialFood = true;

        // Act
        Food food = new Food(snake, wall, null, isSpecialFood, "");

        // Assert
        assertTrue(food.isSpecialFood());
        assertNotEquals(0, food.getSpecialFoodTimeToLive());
        assertNotNull(food.getFoodType());
        assertNotNull(food.getLocation());
        assertTrue(food.getScoreValue() > 0);
    }

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void constructorSpecialFoodCreationHard(boolean isHorizontal) {
        // Arrange
        StateManager.setDifficulty(Constants.Difficulty.HARD);
        Snake snake = new Snake(4, Constants.Direction.RIGHT);
        Wall wall = new Wall(isHorizontal, 1, 1, 3);
        boolean isSpecialFood = true;

        // Act
        Food food = new Food(snake, wall, null, isSpecialFood, "");

        // Assert
        assertTrue(food.isSpecialFood());
        assertNotEquals(0, food.getSpecialFoodTimeToLive());
        assertNotNull(food.getFoodType());
        assertNotNull(food.getLocation());
        assertTrue(food.getScoreValue() > 0);
    }

    @Test
    void constructorFoodDoesNotCollideWithSnake() {
        // Arrange
        Snake snake = new Snake(4, Constants.Direction.RIGHT);
        Wall wall = new Wall(true, 1, 1, 3);
        boolean isSpecialFood = false;

        // Act
        Food food = new Food(snake, wall, null, isSpecialFood, "");

        // Assert
        for (Position snakeSegment : snake.getSegments()) {
            assertNotEquals(snakeSegment, food.getLocation());
        }
    }

    @Test
    void constructorFoodDoesNotCollideWithPreviousFood() {
        // Arrange
        Snake snake = new Snake(4, Constants.Direction.RIGHT);
        Wall wall = new Wall(true, 1, 1, 3);
        boolean isSpecialFood = false;
        Food previousFood = new Food(snake, wall, null, isSpecialFood, "");

        // Act
        Food newFood = new Food(snake, wall, previousFood, isSpecialFood, "");

        // Assert
        assertNotEquals(previousFood.getLocation(), newFood.getLocation());
    }
}