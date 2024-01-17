package at.ac.fhcampuswien.snake.service;

import at.ac.fhcampuswien.snake.util.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;
import org.slf4j.Logger;

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
    void savePlayerHighScore_savesPlayer_whenFileDoesNotExist() throws IOException {
        // Arrange
        List<String> expectedPlayerHighScoreStrings = List.of("John Doe%%%100");
        Player player = new Player("John Doe", 100);

        // Act
        HighScoreService.savePlayerHighScore(player, highScoreFilePath);

        // Assert
        List<String> actualPlayerHighScoreStrings = Files.readAllLines(Path.of(highScoreFilePath));
        assertIterableEquals(expectedPlayerHighScoreStrings, actualPlayerHighScoreStrings);
    }

    @Test
    void savePlayerHighScore_savesPlayer_whenFileIsEmpty() throws IOException {
        // Arrange
        List<String> expectedPlayerHighScoreStrings = List.of("John Doe%%%100");
        File highScoreFile = new File(highScoreFilePath);
        highScoreFile.createNewFile();
        Player player = new Player("John Doe", 100);

        // Act
        HighScoreService.savePlayerHighScore(player, highScoreFilePath);

        // Assert
        List<String> actualPlayerHighScoreStrings = Files.readAllLines(Path.of(highScoreFilePath));
        assertIterableEquals(expectedPlayerHighScoreStrings, actualPlayerHighScoreStrings);

    }

   @Test
    void savePlayerHighScore_savesTopFivePlayers_whenFileHasMoreThanFivePlayers() throws IOException {
        // Arrange
       List<String> expectedPlayerHighScoreStrings = List.of("John Doe%%%600", "Player5%%%500", "Player4%%%400",
               "Player3%%%300", "Player2%%%200");
        File highScoreFile = new File(highScoreFilePath);
        highScoreFile.createNewFile();
        Files.write(highScoreFile.toPath(), List.of("Player1%%%100", "Player2%%%200", "Player3%%%300", "Player4%%%400",
                "Player5%%%500"));
        Player player = new Player("John Doe", 600);

        // Act
        HighScoreService.savePlayerHighScore(player, highScoreFilePath);

        // Assert
        List<String> actualPlayerHighScoreStrings = Files.readAllLines(highScoreFile.toPath());
        assertIterableEquals(expectedPlayerHighScoreStrings, actualPlayerHighScoreStrings);
    }

    @Test
    void savePlayerHighScore_doesNotSavePlayer_whenScoreIsNotInTopFive() throws IOException {
        // Arrange
        List<String> expectedPlayerHighScoreStrings = List.of("Player5%%%500", "Player4%%%400", "Player3%%%300",
                "Player2%%%200", "Player1%%%100");
        File highScoreFile = new File(highScoreFilePath);
        highScoreFile.createNewFile();
        Files.write(highScoreFile.toPath(), List.of("Player1%%%100", "Player2%%%200", "Player3%%%300", "Player4%%%400",
                "Player5%%%500"));
        Player player = new Player("John Doe", 50);

        // Act
        HighScoreService.savePlayerHighScore(player, highScoreFilePath);

        // Assert
        List<String> actualPlayerHighScoreStrings = Files.readAllLines(highScoreFile.toPath());
        assertIterableEquals(expectedPlayerHighScoreStrings, actualPlayerHighScoreStrings);
    }

    @Test
    void savePlayerHighScore_logsError_whenIOExceptionIsThrown() {
        // Arrange
        String invalidPath = "\0";
        Player player = new Player("John Doe", 100);
        Logger logMock = Mockito.mock(Logger.class);
        HighScoreService highScoreService = new HighScoreService(logMock);

        // Act
        highScoreService.savePlayerHighScore(player, invalidPath);

        // Assert
        Mockito.verify(logMock).error(Mockito.eq("Error while saving the players list"), Mockito.any(IOException.class));
    }

    @Test
    void getSavedPlayerList_returnsCorrectList_whenPathIsValid() throws IOException {
        // Arrange
        List<Player> expectedPlayers = List.of(
                new Player("Jane Doe", 200),
                new Player("Bob", 180),
                new Player("Alice", 150),
                new Player("John Doe", 100)
        );
        File highScoreFile = new File(highScoreFilePath);
        highScoreFile.createNewFile();
        Files.write(highScoreFile.toPath(), List.of("Jane Doe%%%200", "Bob%%%180", "Alice%%%150", "John Doe%%%100"));

        // Act
        List<Player> actualPlayers = HighScoreService.getSavedPlayerList(highScoreFilePath);

        // Assert
        assertIterableEquals(expectedPlayers, actualPlayers);
    }

    @Test
    void getSavedPlayerList_returnsNull_whenPathIsInvalid() {
        // Arrange
        String invalidPath = "\0";

        // Act
        List<Player> actualPlayers = HighScoreService.getSavedPlayerList(invalidPath);

        // Assert
        assertNull(actualPlayers);
    }

    @Test
    void getSavedPlayerList_logsError_whenIOExceptionIsThrown() {
        // Arrange
        String invalidPath = "\0";
        Logger logMock = Mockito.mock(Logger.class);
        HighScoreService highScoreService = new HighScoreService(logMock);

        // Act
        highScoreService.getSavedPlayerList(invalidPath);

        // Assert
        Mockito.verify(logMock).error(Mockito.eq("Error getting the saved players list"), Mockito.any(IOException.class));
    }
}