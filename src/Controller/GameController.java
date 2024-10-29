package Controller;

import Model.Game;
import Model.Player;
import View.GameView;

import java.util.Random;
import java.util.Scanner;

public class GameController {
    private final Game game;
    private final GameView view;
    private Player player;
    private final String[] words;
    private int winstreak = 0;
    private int difficultyLevel = 5;
    private int highestScoreThisSession = 0;

    public GameController(Game game, GameView view, String[] words) {
        this.game = game;
        this.view = view;
        this.words = words;
        this.player = game.logIn("default"); // Or handle login differently
    }

    public void startGame() {
        changeProfile();
        while (true) {
            view.displayMenu(winstreak, player);
            int choice = view.getUserChoice(1, 7);
            processChoice(choice);
        }
    }

    private void processChoice(int choice) {
        Random random = new Random();
        switch (choice) {
            case 1 -> { // Play Wordle
                int answerIndex = random.nextInt(words.length);
                String answerWord = words[answerIndex];
                game.startNewGame(answerWord, difficultyLevel);
                int score = playWordle();
                updateScores(score);
            }
            case 2 -> setDifficultyLevel();
            case 3 -> leaderboard();
            case 4 -> changeProfile();
            case 5 -> showInstructions();
            case 6 -> changePlatform();
            case 7 -> exitGame();
        }
    }
    private void leaderboard() {
        Scanner scanner = new Scanner(System.in);
        GameView.leaderboard(game);
        scanner.nextLine();
    }
    private void showInstructions() {
        Scanner scanner = new Scanner(System.in);
        GameView.showInstructions();
        scanner.nextLine();
    }
    // Play Wordle game (stub method)
    private int playWordle() {
        Scanner scanner = new Scanner(System.in);
        while (game.hasAttemptsLeft()) {
            int i;
            // print used trees, starts with 0, gets more in 'successfull tries
            //print unused trees, starts with 5, gets smaller with each try
            for (i = game.getAttempts(); i > 0; i--) {
                view.printBlankBoard();
            }
            GameView.getUserGuess();
            String guess = scanner.nextLine().toLowerCase();

            if (!game.isWordInDictionary(guess)) {
                view.showInvalidWordMessage();
                continue;
            }

            int[] result = game.checkGuess(guess);
            view.updateBoard(guess, result);

            if (game.isGameWon(result)) {
                view.showWinMessage();
                return 1;
            }
        }
        view.showLoseMessage(game.getAnswer());
        return 0;
    }
    private void updateScores(int score) {
        if (score > 1) {
            winstreak++;
        } else {
            winstreak = 0;
        }
        if (winstreak > highestScoreThisSession) {
            highestScoreThisSession = winstreak;
        }
    }

    private void setDifficultyLevel() {
        GameView.displayDifficulty();

        Scanner scanner = new Scanner(System.in);
        int level = scanner.nextInt();
        if (level == 1) {
            difficultyLevel = 7;
        }   else if (level ==2) {
            difficultyLevel = 5;
        }   else if (level == 3) {
            difficultyLevel = 4;
        }
    }

    private void changeProfile() {
        // Logic to change profile
        Scanner scanner = new Scanner(System.in);
        GameView.changeProfile();
        player = game.logIn(scanner.nextLine());
    }

    private void changePlatform() {
        // Logic to change platform
    }

    private void exitGame() {
        game.savePlayersToFile();
        // Save other state if necessary
        System.exit(0);
    }
}
