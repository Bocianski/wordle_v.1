package View;

import Model.Game;
import Model.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameView {
    private final Scanner scanner = new Scanner(System.in);

    // Display the menu
    public void displayMenu(int winstreak, Player player) {
        logo();
        System.out.println(STR."Welcome, \{player.getName()}");
        System.out.println(STR."Winstreak: \{winstreak}");
        System.out.println("1. Play Wordle");
        System.out.println("2. Change difficulty");
        System.out.println("3. Leaderboard");
        System.out.println("4. Change Profile");
        System.out.println("5. Instruction");
        System.out.println("6. Change Platform");
        System.out.println("7. Exit");
    }

    public static void displayDifficulty() {
        logo();
        System.out.println("Wybierz jeden z poziomów:\n\n1. Łatwy - 7 prób, dla tych którzy dopiero zaczynają z grami słownymi\n2. Średni - domyślny, 5 prób, klasyczne WORDLE które wszyscy kochamy\n3. Trudny - 4 próby, dla weteranów którzy poszukują wyzwań\n");
    }

    // Show results
    public static void leaderboard(Game game) {
        logo();
        System.out.println("Players\t\t\t\tScore");
        for (Player player : game.getPlayers()) {
            if (player.getName().length() < 4) {
                System.out.println(STR."\{player.getName()}\t\t\t\t\t\{player.getScore()}");
            } else {
                System.out.println(STR."\{player.getName()}\t\t\t\t\{player.getScore()}");
            }
        }
        System.out.println("\n\nNaciśnij ENTER by kontynuować");
    }

    // Get user input
    public int getUserChoice(int min, int max) {
        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                choice = scanner.nextInt();

                if (choice >= min && choice <= max) {
                    validInput = true;  // Input is within the range
                } else {
                    System.out.println(STR."Invalid choice. Please enter a number between \{min} and \{max}: ");
                }
            } catch (InputMismatchException e) {
                System.out.println(STR."Invalid input. Please enter a number between \{min} and \{max}: ");
                scanner.next();  // Clear the invalid input
            }
        }
        return choice;
    }

    public void getUserGuessPrompt() {
        System.out.print("Enter your 5-letter guess: ");
    }

    // Update the board with a specific guessed word and result colors
    public void updateBoard(String guess, int[] result) {
        printTopOrBottomBorder(result);      // Print the top border of each cell with colors
        printMiddleRowWithResult(guess, result);  // Print the row with letters and color-coding
        printTopOrBottomBorder(result);      // Print the bottom border of each cell with colors
    }

    // Method to print blank board rows for unused attempts
    public void printBlankBoard() {
        printTopOrBottomBorder(null);  // No color for blank rows
        printEmptyMiddleRow();         // Blank row
        printTopOrBottomBorder(null);  // No color for blank rows
    }
    // Display a message if the user enters an invalid word or incorrect input
    public void showInvalidWordMessage() {
        System.out.println(ANSI_RED + "No nie weszło! Spróbuj jeszcze raz." + ANSI_RESET);
        promptEnterToContinue();
    }

    // Display a win message if the player guesses correctly
    public void showWinMessage() {
        System.out.println(ANSI_GREEN + "Gratulacje! Zrobiłeś to!" + ANSI_RESET);
        promptEnterToContinue();
    }

    // Display a loss message with the correct answer if the player runs out of attempts
    public void showLoseMessage(String answer) {
        System.out.println(ANSI_RED + "Niestety przegrałeś. Odpowiedzią było: " + answer + ANSI_RESET);
        promptEnterToContinue();
    }

    // Helper method to pause until the user presses Enter
    private void promptEnterToContinue() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();  // Waits for the user to press Enter
    }

    // Helper method to print top and bottom borders of the cells
    private void printTopOrBottomBorder(int[] result) {
        for (int i = 0; i < 5; i++) {
            String color = (result == null) ? ANSI_WHITE : getColor(result[i]);
            System.out.print(color + "\t*---*" + ANSI_RESET);
        }
        System.out.println();
    }

    // Helper method to print an empty middle row of cells for unused attempts
    private void printEmptyMiddleRow() {
        for (int i = 0; i < 5; i++) {
            System.out.print(ANSI_WHITE + "\t|   |" + ANSI_RESET);
        }
        System.out.println();
    }

    // Helper method to print the middle row with guessed word characters color-coded
    private void printMiddleRowWithResult(String guess, int[] result) {
        for (int i = 0; i < guess.length(); i++) {
            String color = getColor(result[i]);
            System.out.print(color + "\t| " + guess.charAt(i) + " |" + ANSI_RESET);
        }
        System.out.println();
    }
    // Other display methods as needed (e.g., instructions, error messages, etc.)

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";

    private String getColor(int status) {
        return switch (status) {
            case 2 -> ANSI_GREEN;  // Correct position and letter
            case 1 -> ANSI_YELLOW; // Present in word but wrong position
            default -> ANSI_RED;   // Not present in word
        };
    }


    // Helper method to print middle row with word characters
    private void printMiddleRowWithWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            System.out.print(STR."\{ANSI_GREEN}\t| \{word.charAt(i)} | \{ANSI_RESET}");
        }
        System.out.println();
    }

    // Helper method to print cell rows with dynamic content


    // Helper method to print the actual values in the cells

    public static void logo() {
        cls();
        System.out.println(STR."\{ANSI_BLUE}\n\tW     WWW     W     OOO    RRR    DDD    L      EEEEE	");
        System.out.println("\tW     W W     W    O   O   R  R   D  D   L      E	");
        System.out.println("\t W   W   W   W    O     O  RRR    D   D  L      EEEE	");
        System.out.println("\t W   W   W   W    O     O  R R    D   D  L      E	");
        System.out.println("\t  W W     W W      O   O   R  R   D  D   L      E");
        System.out.println(STR."\t  WWW     WWW       OOO    R   R  DDD    LLLLL  EEEEE	\n\{ANSI_RESET}");
    }

    public static void showInstructions() {
        logo();
        System.out.println(STR."\n\n\tW grze WORDLE należy znaleźć wyraz sposobem \"prób i błędów\".\n\tKolor \{ANSI_RED}czerwony \{ANSI_RESET}oznacza, że litery nie ma w wyrazie\n\tKolor \{ANSI_YELLOW}żółty \{ANSI_RESET}oznacza, że litera jest w wyrazie na innym polu\n\tKolor \{ANSI_GREEN}zielony \{ANSI_RESET}oznacza,że litera jest na swoim miejscu\n\t\{ANSI_YELLOW_BACKGROUND}\{ANSI_BLACK}Wiedz że litera może się pojawić więcej niż jeden raz w wyrazie\{ANSI_RESET}\n\nPamiętaj by wybory podejmować liczbami!\n\nNaciśnij ENTER by kontynuować");
    }

    public static void changeProfile() {
        logo();
        System.out.println("Podaj login by sie zalogować, lub by stworzyć nowe konto :) \n");
    }

    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getUserGuess() {
        System.out.print("Podaj 5-literowy wyraz: ");
    }

}