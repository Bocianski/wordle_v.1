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

    // Other display methods as needed (e.g., instructions, error messages, etc.)

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[47m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private String getColor(int status) {
        return switch (status) {
            case 1 -> ANSI_YELLOW;
            case 2 -> ANSI_GREEN;
            default -> ANSI_RED;
        };
    }
    public void printWordBoard(String rightWord) {
        printTopOrBottomBorder();
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                printMiddleRowWithWord(rightWord);  // Middle row with word
            } else {
                printEmptyMiddleRow();              // Empty rows
            }
        }
        printTopOrBottomBorder();
        System.out.println();
    }

    // Method to print a blank board
    public void printBlankBoard() {
            printTopOrBottomBorder();
            printEmptyMiddleRow();
            printTopOrBottomBorder();
    }

    // Method to print the board during the game with character guesses and colors
    public void printGameBoard(char[][] arr, int[][] tab, int rowsToPrint) {
        for (int i = 0; i < rowsToPrint; i++) {
            printRow(tab[i], "top");      // Print top part of each cell
            printRow(tab[i], "middle");   // Print middle part (empty space)
            printValues(arr[i], tab[i]);  // Print values in the middle
            printRow(tab[i], "middle");   // Print middle part (empty space)
            printRow(tab[i], "bottom");   // Print bottom part of each cell
            System.out.println();
        }
    }

    // Helper method to print top and bottom borders of the cells
    private void printTopOrBottomBorder(String statte) {
        if//lepiej chyba stan zrobic na mapach
        for (int i = 0; i < 5; i++) {
            System.out.print(STR."\{ANSI_WHITE}\t*---* \{ANSI_RESET}");
        }
        System.out.println();
    }

    // Helper method to print empty middle row of cells
    private void printEmptyMiddleRow() {
        for (int i = 0; i < 5; i++) {
            System.out.print(STR."\{ANSI_GREEN}\t|   | \{ANSI_RESET}");
        }
        System.out.println();
    }

    // Helper method to print middle row with word characters
    private void printMiddleRowWithWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            System.out.print(STR."\{ANSI_GREEN}\t| \{word.charAt(i)} | \{ANSI_RESET}");
        }
        System.out.println();
    }

    // Helper method to print cell rows with dynamic content
    private void printRow(int[] row, String part) {
        for (int i : row) {
            String color = getColor(i);
            switch (part) {
                case "top":
                case "bottom":
                    System.out.print(STR."\{color}\t----- \{ANSI_RESET}");
                    break;
                case "middle":
                    System.out.print(STR."\{color}\t-   - \{ANSI_RESET}");
                    break;
            }
        }
        System.out.println();
    }

    // Helper method to print the actual values in the cells
    private void printValues(char[] values, int[] row) {
        for (int i = 0; i < row.length; i++) {
            String color = getColor(row[i]);
            char value = values[i];
            switch (row[i]) {
                case 0:
                    System.out.print(STR."\{color}\t- \{value} - \{ANSI_RESET}");
                    break;
                case 1:
                    System.out.print(STR."\{color}\t$ \{value} $ \{ANSI_RESET}");
                    break;
                case 2:
                    System.out.print(STR."\{color}\t+ \{value} + \{ANSI_RESET}");
                    break;
            }
        }
        System.out.println();
    }

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
    public void showInvalidWordMessage() {
        System.out.println(STR."\{ANSI_RED}Wyraz nie pasuje! Spróbuj jeszcze raz.\{ANSI_RESET}");
    }
    public void showWinMessage() {
        System.out.println(STR."\{ANSI_GREEN}Gratulacje! Udało ci się wygrać ze słownikiem.\{ANSI_RESET}");
    }

    public void showLoseMessage(String answer) {
        System.out.println(STR."\{ANSI_RED}Przykro nam ale skończyły ci się próby. Odpowiedź to: \{answer}\{ANSI_RESET}");
    }
    // -------------------------------------------------------------------------
    public void updateBoard(String guess, int[] result) {
        System.out.println("Board:");
        for (int i = 0; i < 5; i++) {
            if (result[i] == 2) {
                System.out.print(STR."\{ANSI_GREEN}\{guess.charAt(i)} \{ANSI_RESET}");  // Correct
            } else if (result[i] == 1) {
                System.out.print(STR."\{ANSI_YELLOW}\{guess.charAt(i)} \{ANSI_RESET}");  // Present
            } else {
                System.out.print(STR."\{ANSI_RED}\{guess.charAt(i)} \{ANSI_RESET}");  // Incorrect
            }
        }
        System.out.println();
    }
    //------------------------------------------------------------------------------
}
