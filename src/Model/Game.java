package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Model.Player;
import java.util.Scanner;

public class Game {
    private final List<Player> players;
    private int attemptsLeft;
    private String answer;
    private char[] answerChars;
    private final String[] dictionary;

    // Constructor for setting up the game with players and loading from files
    public Game(String[] dictionary, int difficulty) {
        this.players = new ArrayList<>();
        this.dictionary = dictionary;
        this.attemptsLeft = difficulty;
        loadPlayersFromFile();
    }

    // Method to initialize a new Wordle round
    public void startNewGame(String answer, int difficulty) {
        this.answer = answer;
        this.answerChars = answer.toCharArray();
        this.attemptsLeft = difficulty;
    }

    // Check if a word is in the dictionary
    public boolean isWordInDictionary(String guess) {
        for (String word : dictionary) {
            if (word.equals(guess)) {
                return true;
            }
        }
        return false;
    }

    // Method to check a player's guess
    public int[] checkGuess(String guess) {
        int[] result = new int[5];  // 0 = wrong, 1 = present, 2 = correct
        char[] guessChars = guess.toCharArray();

        // Check for correct letters (green)
        for (int i = 0; i < 5; i++) {
            if (guessChars[i] == answerChars[i]) {
                result[i] = 2;  // Correct position and letter
            }
        }

        // Check for present letters (yellow)
        for (int i = 0; i < 5; i++) {
            if (result[i] != 2) {
                for (int j = 0; j < 5; j++) {
                    if (guessChars[i] == answerChars[j] && result[j] != 2) {
                        result[i] = 1;  // Letter is present but wrong position
                        break;
                    }
                }
            }
        }
        attemptsLeft--;
        return result;
    }

    // Method to check if game is won
    public boolean isGameWon(int[] result) {
        for (int res : result) {
            if (res != 2) {
                return false;
            }
        }
        return true;
    }

    // Check if there are attempts left
    public boolean hasAttemptsLeft() {
        return attemptsLeft > 0;
    }

    // Retrieve the answer
    public String getAnswer() {
        return answer;
    }
    public int getAttempts() {
        return attemptsLeft;
    }

    // Methods for Player Management
    public Player logIn(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        // If player not found, create a new one
        Player newPlayer = new Player(name);
        players.add(newPlayer);
        return newPlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    // Save players to a file
    public void savePlayersToFile() {
        String filePath = "src/Resources/players.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(filePath)) {
            PlayersData data = new PlayersData(players);
            gson.toJson(data, writer);  // Serialize players list to JSON
        } catch (IOException e) {
            System.out.println("Error saving players to the JSON file.");
        }
    }

    // Load players from a file
    public void loadPlayersFromFile() {
        String filePath = "src/Resources/players.json";
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(filePath)) {
            // Deserialize JSON to a list of players
            PlayersData data = gson.fromJson(reader, PlayersData.class);
            players.addAll(data.getPlayers());
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("Error reading the JSON file. Starting with an empty player list.");
        }
    }

    public class PlayersData {
        private List<Player> players;

        // Constructor to initialize the players list
        public PlayersData(List<Player> players) {
            this.players = players;
        }

        // Default constructor (needed by Gson for deserialization)
        public PlayersData() {
        }

        public List<Player> getPlayers() {
            return players;
        }

        public void setPlayers(List<Player> players) {
            this.players = players;
        }
    }

}
