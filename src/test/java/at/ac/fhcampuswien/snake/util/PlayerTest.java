package at.ac.fhcampuswien.snake.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    @Test
    void constructor() {
        // Arrange
        String expectedPlayerName = "John Doe";
        int expectedPlayerScore = 100;

        // Act
        Player player = new Player(expectedPlayerName, expectedPlayerScore);
        String actualPlayerName = player.getName();
        int actualPlayerScore = player.getScore();

        // Assert
        assertEquals(expectedPlayerName, actualPlayerName);
        assertEquals(expectedPlayerScore, actualPlayerScore);
    }

    @Test
    void toHighScoreString() {
        // Arrange
        String expectedHighScoreString = "John Doe%%%100";
        String playerName = "John Doe";
        int playerScore = 100;

        // Act
        Player player = new Player(playerName, playerScore);
        String actualHighScoreString = player.toHighScoreString();

        // Assert
        assertEquals(expectedHighScoreString, actualHighScoreString);
    }

    @Test
    void toString_returnsExpectedFormat() {
        // Arrange
        String expected = "Player{name=John Doe, score=100}";
        Player player = new Player("John Doe", 100);

        // Act
        String actual = player.toString();

        // Assert
        assertEquals(expected, actual);
    }
}