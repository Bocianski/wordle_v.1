import Controller.GameController;
import Model.Game;
import View.GameView;

import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Main {
    public static void main(String[] args) {
        String[] words = readWordsFromFile(); //read from file and keep it here
        Game game = new Game(words, 5); //game object
        game.loadPlayersFromFile(); //players from file to string and create Player objects and add to list in game


        GameView view = new GameView(); // view init
        GameController controller = new GameController(game, view, words); //control init

        controller.startGame(); // Start the game loop
    }

    // Method to read words from file (stub)
    public static String[] readWordsFromFile() {
        String filePath = "src/Resources/words.json";
        Gson gson = new Gson();
        String[] words = new String[0];

        try (FileReader reader = new FileReader(filePath)) {
            // Deserialize JSON to an array of words
            WordsData data = gson.fromJson(reader, WordsData.class);
            words = data.getWords();
        } catch (IOException | JsonSyntaxException e) {
            System.err.println("Error reading the JSON file: " + e.getMessage());
        }

        return words;
    }
    // Inner class to match the JSON structure
    private static class WordsData {
        private String[] words;

        public String[] getWords() {
            return words;
        }

        public void setWords(String[] words) {
            this.words = words;
        }
    }
}
