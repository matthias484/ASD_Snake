package at.ac.fhcampuswien.snake.service;

import at.ac.fhcampuswien.snake.util.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static at.ac.fhcampuswien.snake.util.Constants.HIGHSCORE_SEPARATOR;

/**
 * Utility Class to read from and save to a text file that stores the high scores.
 */
public class HighScoreService {

    private static final Logger LOG = LoggerFactory.getLogger(HighScoreService.class);

    /**
     * Private constructor to hide the implicit public one.
     */
    private HighScoreService() {

    }

    /**
     * This method checks if a high scores file exists in the specified path.
     * If the file does not exist, it creates a new one.
     *
     * @return A File object that represents the high scores file.
     * @throws IOException if an I/O error occurs when creating the high scores file.
     */
    private static File getHighScoresFile(String path) throws IOException {
        File highScoreFile = new File(path);
        if (!highScoreFile.exists()) {
            createHighScoresFile(highScoreFile);
        }
        return highScoreFile;
    }

    /**
     * This method creates a new high scores file.
     * If the file cannot be created, it throws an IOException.
     *
     * @param highScoreFile A File object representing the high scores file to be created.
     * @throws IOException if the file cannot be created.
     */
    private static void createHighScoresFile(File highScoreFile) throws IOException {
        boolean fileCreated = highScoreFile.createNewFile();
        if (!fileCreated) {
            throw new IOException("Could not create new high score file.");
        }
    }

    /**
     * This method reads every line from the provided file and stores it in a list.
     *
     * @param file The file to read from.
     * @return List of strings where each string represents a line in the file.
     * @throws IOException if an I/O error occurs when reading the file content.
     */
    private static List<String> getFileContent(File file) throws IOException {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            LOG.error("Error reading the file content");
            throw new IOException(e);
        }
    }

    /**
     * This method takes a list of strings, where each string represents a line from the high scores file.
     * Each line is split into parts using the high score separator.
     * Each part is then used to create a new Player object.
     *
     * @param highScoreList The list of strings to be processed.
     * @return A list of Player objects created from the provided list of strings.
     */
    private static List<Player> getPlayerFromList(List<String> highScoreList) {
        List<Player> unmodifiableList = highScoreList.stream()
                .filter(line -> line.contains(HIGHSCORE_SEPARATOR))
                .map(line -> line.split(HIGHSCORE_SEPARATOR))
                .map(parts -> new Player(parts[0], Integer.parseInt(parts[1])))
                .toList();
        return new ArrayList<>(unmodifiableList);
    }

    /**
     * This method retrieves the list of saved players from the high scores file.
     * It reads the file content and converts each line into a Player object.
     *
     * @return A list of Player objects representing the saved players.
     *         Returns null if an I/O error occurs when reading the file content.
     */
    public static List<Player> getSavedPlayerList(String path) {
        List<Player> savedPlayerList = null;
        try {
            File highScoreFile = getHighScoresFile(path);
            List<String> fileContent = getFileContent(highScoreFile);
            savedPlayerList = getPlayerFromList(fileContent);
        } catch (IOException ex) {
            LOG.error("Error getting the saved players list", ex);
        }
        return savedPlayerList;
    }

    /**
     * This method saves the high score of the current player.
     * It retrieves the list of saved players from the high scores file,
     * adds the current player to the list, and then writes the updated list back to the file.
     * Only the top five players by score are saved.
     * An error message will be created if the file can not be read or the path to it is wrong.
     *
     * @param currentPlayer The current player whose high score is to be saved.
     */
    public static void savePlayerHighScore(Player currentPlayer, String path) {
        try {
            File highscoreFile = getHighScoresFile(path);
            List<String> fileContent = getFileContent(highscoreFile);
            List<Player> players = getPlayerFromList(fileContent);

            players.add(currentPlayer);

            List<String> topFivePlayersByScoreAsHighScoreStringList = getTopFivePlayersByScoreAsHighScoreStringList(players);
            Files.write(highscoreFile.toPath(), topFivePlayersByScoreAsHighScoreStringList);
        } catch (IOException ex) {
            LOG.error("Error while saving the players list", ex);
        }
    }

    /**
     * This method sorts the list of players in descending order by score and selects the top five.
     * Each player is then converted to a string in the format "name%%%score".
     *
     * @param players The list of Player objects to be processed.
     * @return A list of strings representing the top five players by score.
     */
    private static List<String> getTopFivePlayersByScoreAsHighScoreStringList(List<Player> players) {
        return players.stream()
                .sorted(sortByScoreDescending())
                .limit(5)
                .map(Player::toHighScoreString)
                .toList();
    }

    /**
     * This method creates a comparator for Player objects that sorts them in descending order by score.
     *
     * @return A Comparator for Player objects.
     */
    private static Comparator<Player> sortByScoreDescending() {
        return Comparator.comparingInt(Player::getScore).reversed();
    }
}
