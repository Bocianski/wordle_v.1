import Controller.GameController;
import Model.Game;
import View.GameView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        String filePath = "src/Resources/words.txt";
        String[] words = new String[0];

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read the first line to get the number of words
            int numOfWords = Integer.parseInt(br.readLine().trim());
            words = new String[numOfWords];

            // Read the remaining lines to fill the words array
            for (int i = 0; i < numOfWords; i++) {
                words[i] = br.readLine().trim();  // Add each word to the array
            }
        } catch (IOException e) {
            System.err.println(STR."Error reading the file: \{e.getMessage()}");
        }

        return words;
    }
}
