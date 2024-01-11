package at.ac.fhcampuswien.snake.service;

import at.ac.fhcampuswien.snake.util.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HighScoreServiceTest {

    @TempDir
    Path tempDir;

    private String highScoreFilePath;

    @BeforeEach
    void setUp() {
        highScoreFilePath = tempDir.resolve("highScores.txt").toString();
    }

    @Test
    void savePlayerHighScore_savesPlayer_whenFileIsEmpty() throws IOException {
        // Arrange
        File highScoreFile = new File(highScoreFilePath);
        highScoreFile.createNewFile();
        Player player = new Player("John Doe", 100);

        // Act
        HighScoreService.savePlayerHighScore(player, highScoreFilePath);

        // Assert
        List<String> lines = Files.readAllLines(Path.of(highScoreFilePath));
        assertEquals(1, lines.size());
        assertEquals("John Doe%%%100", lines.get(0));
    }

   @Test
    void savePlayerHighScore_savesTopFivePlayers_whenFileHasMoreThanFivePlayers() throws IOException {
        // Arrange
        File highScoreFile = new File(highScoreFilePath);
        highScoreFile.createNewFile();
        Files.write(highScoreFile.toPath(), List.of("Player1%%%100", "Player2%%%200", "Player3%%%300", "Player4%%%400", "Player5%%%500"));
        Player player = new Player("John Doe", 600);

        // Act
        HighScoreService.savePlayerHighScore(player, highScoreFilePath);

        // Assert
        List<String> lines = Files.readAllLines(highScoreFile.toPath());
        assertEquals(5, lines.size());
        assertEquals("John Doe%%%600", lines.get(0));
        assertEquals("Player5%%%500", lines.get(1));
        assertEquals("Player4%%%400", lines.get(2));
        assertEquals("Player3%%%300", lines.get(3));
        assertEquals("Player2%%%200", lines.get(4));
    }

    @Test
    void savePlayerHighScore_doesNotSavePlayer_whenScoreIsNotInTopFive() throws IOException {
        // Arrange
        File highScoreFile = new File(highScoreFilePath);
        highScoreFile.createNewFile();
        Files.write(highScoreFile.toPath(), List.of("Player1%%%100", "Player2%%%200", "Player3%%%300", "Player4%%%400", "Player5%%%500"));
        Player player = new Player("John Doe", 50);

        // Act
        HighScoreService.savePlayerHighScore(player, highScoreFilePath);

        // Assert
        List<String> lines = Files.readAllLines(highScoreFile.toPath());
        assertEquals(5, lines.size());
        assertEquals("Player5%%%500", lines.get(0));
        assertEquals("Player4%%%400", lines.get(1));
        assertEquals("Player3%%%300", lines.get(2));
        assertEquals("Player2%%%200", lines.get(3));
        assertEquals("Player1%%%100", lines.get(4));
    }
}