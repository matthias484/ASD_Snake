package at.ac.fhcampuswien.snake.util;

import static at.ac.fhcampuswien.snake.util.Constants.HIGHSCORE_SEPARATOR;

public record Player(String name, int score) {

    public String toHighScoreString() {
        return name + HIGHSCORE_SEPARATOR + score;
    }

    @Override
    public String toString() {
        return "Player{name=" + name + ", score=" + score + '}';
    }
}
