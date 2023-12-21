package at.ac.fhcampuswien.snake.service;

import at.ac.fhcampuswien.snake.util.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static at.ac.fhcampuswien.snake.util.Constants.HIGHSCORE_SEPARATOR;

/**
 * Utility Class to read from and save to a text file that stores the high scores.
 */
public class HighscoreService {

    private static final Logger LOG = LoggerFactory.getLogger(HighscoreService.class);

    /**
     * Private constructor to hide the implicit public one.
     */
    private HighscoreService() {

    }

    /**
     * This method checks if a high scores file exists in the specified path.
     * If the file does not exist, it creates a new one.
     *
     * @return A File object that represents the high scores file.
     * @throws IOException if an I/O error occurs when creating the high scores file.
     */
    private static File getHighscoresFile() throws IOException {
        String path = "src/main/resources/highScores.txt";
        File highScoreFile = new File(path);
        if (!highScoreFile.exists()) {
            createHighscoresFile(highScoreFile);
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
    private static void createHighscoresFile(File highScoreFile) throws IOException {
        boolean fileCreated = highScoreFile.createNewFile();
        if (!fileCreated) {
            throw new IOException("Could not create new high score file.");
        }
    }

    /**
     * This method reads every line from the file and stores it in a list.
     *
     * @param file The file to read from.
     * @return List of strings where each row represents a line in the file.
     */
    private static List<String> getFileContent(File file) throws IOException{
        List<String> ret = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                ret.add(line);
            }

        } catch (IOException e) {
            LOG.error("Error reading the file content");
            throw new IOException(e);
        }

        return ret;
    }

    /**
     * This method reads the entries in the saved file and splits it according to the defined separator.
     *
     * @param list The list that contains the previous high score data.
     * @return List of players and there score.
     */
    private static List<Player> getPlayerFromList(List<String> list) {
        List<Player> ret = new ArrayList<>();

        if (!list.isEmpty()) {
            for (String line : list) {
                String[] parts = line.split(HIGHSCORE_SEPARATOR);
                Player player = new Player(parts[0], Integer.parseInt(parts[1]));
                ret.add(player);
            }
        }
        return ret;
    }

    public static List<Player> getSavedPlayerList() {
        List<Player> ret = null;

        try {
            File highscoreFile = getHighscoresFile();
            List<String> fileContent = getFileContent(highscoreFile);
            ret = getPlayerFromList(fileContent);
        } catch (IOException ex) {
            LOG.error("Error getting the saved players list");
            ex.printStackTrace();
        }
        return ret;
    }

    /**
     * This method checks if there are past high scores saved in the high scores file.
     * If there are no entries, it will write the name of the player in it.
     * If there are more than five high scores saved, only the five highest scores will be displayed and saved.
     * An error message will be created if the file can not be read or the path to it is wrong.
     *
     * @param currentPlayer The player to save the current player.
     */
    public static void savePlayerHighscore(Player currentPlayer) {

        try {
            File highscoreFile = getHighscoresFile();
            List<String> fileContent = getFileContent(highscoreFile);
            List<Player> players = getPlayerFromList(fileContent);

            if (!players.isEmpty()) {
                players.add(currentPlayer);

                players = players.stream()
                        .sorted(Comparator.comparingInt(Player::getScore).reversed())
                        .collect(Collectors.toList());

                if (players.size() > 5) {
                    Player previousLastPlayer = players.get(players.size() - 2);
                    Player lastPlayer = players.get(players.size() - 1);

                    if (lastPlayer.equals(currentPlayer) &&
                            lastPlayer.getScore() == previousLastPlayer.getScore()) {
                        players.remove(previousLastPlayer);
                    } else players.remove(lastPlayer);
                }
            } else {
                players.add(currentPlayer);
            }

            try (FileWriter fileWriter = new FileWriter(highscoreFile)) {
                fileWriter.write("");
                StringBuilder sb = new StringBuilder();
                for (Player player : players) {
                    sb.append(player.getName());
                    sb.append(HIGHSCORE_SEPARATOR);
                    sb.append(player.getScore());

                    fileWriter.append(sb);
                    fileWriter.append(System.lineSeparator());
                    sb.setLength(0);
                }
            }

        } catch (IOException ex) {
            LOG.error("Error while saving the players list");
            ex.printStackTrace();
        }
    }

}
