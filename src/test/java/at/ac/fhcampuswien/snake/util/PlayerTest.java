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
        String actualPlayerName = player.name();
        int actualPlayerScore = player.score();

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
}