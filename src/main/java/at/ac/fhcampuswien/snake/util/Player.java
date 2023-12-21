package at.ac.fhcampuswien.snake.util;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import static at.ac.fhcampuswien.snake.util.Constants.HIGHSCORE_SEPARATOR;

public class Player {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty score;

    public Player(String name, int score) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(score);
    }

    public String getName() {
        return name.get();
    }

    public int getScore() {
        return score.get();
    }

    public String toHighScoreString() {
        return name.get() + HIGHSCORE_SEPARATOR + score.get();
    }
}
