package at.ac.fhcampuswien.snake.service;

import at.ac.fhcampuswien.snake.util.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class HighscoreServiceTest {

    @Mock
    private File fileMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_createHighScoresFile_createsNewFile() {
        try {
            File highScoreFile = File.createTempFile("test_high_scores", ".txt");
            highScoreFile.delete();
            assertFalse(highScoreFile.exists());

            HighScoreService.createHighScoresFile(highScoreFile);
            assertTrue(highScoreFile.exists());
        } catch (IOException e) {
            fail("An exception occurred: " + e.getMessage());
        }
    }


 /*   @Test
    void testHighScoreServiceConstructor() throws IOException {
        File fileMock = mock(File.class);
        when(fileMock.exists()).thenReturn(false);
        when(fileMock.createNewFile()).thenReturn(true);

        new HighScoreService(fileMock);

        verify(fileMock, times(1)).createNewFile();
    }*/

    // Test for creating a new file
    @Test
    void testCreateHighScoresFile() throws IOException {
        // Arrange
        when(fileMock.createNewFile()).thenReturn(true);

        // Act
        HighScoreService.createHighScoresFile(fileMock);

        // Assert
        verify(fileMock, times(1)).createNewFile();
    }

    // Test for throwing IOException if the file cannot be created
    @Test
    void testThrowsIOExceptionIfFileCannotBeCreated() throws IOException {
        // Arrange
        when(fileMock.createNewFile()).thenReturn(false);

        // Act & Assert
        assertThrows(IOException.class, () -> HighScoreService.createHighScoresFile(fileMock));
    }

    // Test for getting saved player list
    @Test
    void testGetSavedPlayerList() {
        // Arrange & Act
        List<Player> savedPlayerList = HighScoreService.getSavedPlayerList();

        // Assert
        assertNotNull(savedPlayerList);
    }

    // Test for saving player high score
    @Test
    void testSavePlayerHighScore() {
        // Arrange
        Player currentPlayer = new Player("Test Player", 100);

        // Act
        HighScoreService.savePlayerHighScore(currentPlayer);

        // Assert
        List<Player> savedPlayers = HighScoreService.getSavedPlayerList();
        assertNotNull(savedPlayers, "The saved players list should not be null");
    }

    // Test for handling IOException when creating a high scores file
    @Test
    void testCreateHighScoresFileThrowsIOException() throws IOException {
        // Arrange
        when(fileMock.createNewFile()).thenThrow(IOException.class);

        // Act & Assert
        assertThrows(IOException.class, () -> HighScoreService.createHighScoresFile(fileMock));
    }



    @Test
    void testCreateHighScoresFileSuccessfully() throws IOException {
        File fileMock = mock(File.class);
        when(fileMock.exists()).thenReturn(false);
        when(fileMock.createNewFile()).thenReturn(true);

        HighScoreService.createHighScoresFile(fileMock);

        verify(fileMock, times(1)).createNewFile();
    }

    @Test
    void testCreateHighScoresFileFailure() throws IOException {
        File fileMock = mock(File.class);
        when(fileMock.exists()).thenReturn(false);
        when(fileMock.createNewFile()).thenReturn(false);

        assertThrows(IOException.class, () -> HighScoreService.createHighScoresFile(fileMock));
    }

    @Test
    void testGetFileContentThrowsIOException() throws IOException {
        File fileMock = mock(File.class);
        when(fileMock.toPath()).thenReturn(Paths.get("nonexistent_file.txt"));

        assertThrows(IOException.class, () -> HighScoreService.getFileContent(fileMock));
    }

}
