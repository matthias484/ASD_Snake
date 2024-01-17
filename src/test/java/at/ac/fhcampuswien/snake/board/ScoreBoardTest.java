package at.ac.fhcampuswien.snake.board;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static at.ac.fhcampuswien.snake.util.Constants.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ScoreBoardTest {

    @Mock
    private Canvas scoreBoardCanvas;

    @Mock
    private GraphicsContext gc;

    private ScoreBoard scoreBoard;

    @BeforeEach
    void setUp() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        when(scoreBoardCanvas.getGraphicsContext2D()).thenReturn(gc);
        scoreBoard = new ScoreBoard(scoreBoardCanvas);
    }

    @Test
    void drawsScoreBoardWithZeroScore() {
        // Act
        scoreBoard.drawScoreBoard(0);

        // Assert
        verify(gc).setFill(Color.web("4a148c"));
        verify(gc).fillRect(0, 0, SCOREBOARD_WIDTH, SCOREBOARD_HEIGHT);
        verify(gc).setTextAlign(TextAlignment.RIGHT);
        verify(gc).setTextBaseline(VPos.CENTER);
        verify(gc).setFill(Color.WHITE);
        verify(gc).setFont(Font.font("Courier", OBJECT_SIZE_MEDIUM));
        verify(gc).fillText("Score: 0", SCOREBOARD_WIDTH - 7, (double) SCOREBOARD_HEIGHT / 2);
    }

    @Test
    void drawsScoreBoardWithPositiveScore() {
        // Act
        scoreBoard.drawScoreBoard(100);

        // Assert
        verify(gc).setFill(Color.web("4a148c"));
        verify(gc).fillRect(0, 0, SCOREBOARD_WIDTH, SCOREBOARD_HEIGHT);
        verify(gc).setTextAlign(TextAlignment.RIGHT);
        verify(gc).setTextBaseline(VPos.CENTER);
        verify(gc).setFill(Color.WHITE);
        verify(gc).setFont(Font.font("Courier", OBJECT_SIZE_MEDIUM));
        verify(gc).fillText("Score: 100", SCOREBOARD_WIDTH - 7, (double) SCOREBOARD_HEIGHT / 2);
    }
}