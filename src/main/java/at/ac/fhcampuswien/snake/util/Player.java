package at.ac.fhcampuswien.snake.util;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Player{name=" + name.get() + ", score=" + score.get() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name.get(), player.name.get()) &&
                Objects.equals(score.get(), player.score.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.get(), score.get());
    }
}
