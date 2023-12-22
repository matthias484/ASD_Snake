package at.ac.fhcampuswien.snake.util;

public class Constants {

    // Game constants
    public static final String TITLE = "Snake";
    public static final String HIGHSCORE_SEPARATOR = "%%%";
    public static final int INITIAL_SIZE = 4;
    public static final Direction INITIAL_DIRECTION = Direction.RIGHT;

    // Game board constants
    public static final int GAME_BOARD_SIZE_MEDIUM = 500;
    public static final int NUMBER_OF_ROWS_AND_COLS = 20;
    public static final int OBJECT_SIZE_MEDIUM = GAME_BOARD_SIZE_MEDIUM / NUMBER_OF_ROWS_AND_COLS;
    public static final String GAMEBOARD_COLOR_LIGHT = "FFCC66";
    public static final String GAMEBOARD_COLOR_DARK = "CC9933";
    public static final int CENTER_OF_SCREEN_DIVIDER = 2;

    // Scoreboard constants
    public static final int SCOREBOARD_FACTOR = 15;
    public static final int SCOREBOARD_HEIGHT = GAME_BOARD_SIZE_MEDIUM / SCOREBOARD_FACTOR;
    public static final int SCOREBOARD_WIDTH = GAME_BOARD_SIZE_MEDIUM;

    // Highscore board constants
    public static final int HIGHSCORE_BOARD_HEIGHT = 170;
    public static final int HIGHSCORE_BOARD_WIDTH = GAME_BOARD_SIZE_MEDIUM;
    public static final int HIGHSCORE_BOARD_NAME_COL_WIDTH_OFFSET = 125;
    public static final int HIGHSCORE_BOARD_NAME_COL_WIDTH = HIGHSCORE_BOARD_WIDTH - HIGHSCORE_BOARD_NAME_COL_WIDTH_OFFSET;
    public static final int HIGHSCORE_BOARD_SCORE_COL_WIDTH = 100;

    // App constants
    public static final int APP_MENU_BAR_HEIGHT = 25;
    public static final int APP_HEIGHT_MEDIUM = GAME_BOARD_SIZE_MEDIUM + SCOREBOARD_HEIGHT + APP_MENU_BAR_HEIGHT;
    public static final int APP_WIDTH_MEDIUM = GAME_BOARD_SIZE_MEDIUM;

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    public enum Type {
        POINTS, CHARACTER, OBSTACLE
    }

    public enum Difficulty {
        EASY(300), MEDIUM(200), HARD(150);

        private final int refreshTime;

        Difficulty(int refreshTime) {
            this.refreshTime = refreshTime;
        }

        public int getRefreshTime() {
            return refreshTime;
        }
    }
}