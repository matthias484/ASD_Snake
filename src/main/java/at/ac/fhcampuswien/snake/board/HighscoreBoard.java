package at.ac.fhcampuswien.snake.board;

import at.ac.fhcampuswien.snake.service.HighScoreService;
import at.ac.fhcampuswien.snake.util.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.List;

import static at.ac.fhcampuswien.snake.util.Constants.*;

public class HighscoreBoard {

    private final VBox vBox;
    private TableView<Player> table;

    public HighscoreBoard(VBox vBox) {
        this.vBox = vBox;
        initializeVBox();
        createTable();
        configureColumns();
        loadDataIntoTable();
    }

    // Refactor: Extract Method for VBox initialization
    private void initializeVBox() {
        vBox.setMaxHeight(HIGHSCORE_BOARD_HEIGHT);
        vBox.setMaxWidth(HIGHSCORE_BOARD_WIDTH);
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10, 10, 10, 10));
    }

    // Refactor: Extract Method for table creation
    private void createTable() {
        table = new TableView<>();
        vBox.getChildren().add(table);
    }

    // Refactor: Extract Method for configuring columns
    private void configureColumns() {
        TableColumn<Player, String> nameCol = createColumn("Name", "name", HIGHSCORE_BOARD_NAME_COL_WIDTH);
        TableColumn<Player, String> scoreCol = createColumn("Score", "score", HIGHSCORE_BOARD_SCORE_COL_WIDTH);
        table.getColumns().addAll(nameCol, scoreCol);
    }

    // Refactor: Extract Method for creating a TableColumn
    private TableColumn<Player, String> createColumn(String title, String property, double width) {
        TableColumn<Player, String> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setMinWidth(width);
        column.setSortable(false);
        column.setReorderable(false);
        column.setStyle("-fx-alignment: CENTER;");
        return column;
    }

    // Refactor: Extract Method for loading data into table
    private void loadDataIntoTable() {
        try {
            List<Player> playerList = HighScoreService.getSavedPlayerList();
            ObservableList<Player> data = FXCollections.observableArrayList(playerList);
            table.setItems(data);
        } catch (Exception e) {
            // Handle exceptions, e.g., log them or show an error message
        }
    }
}
