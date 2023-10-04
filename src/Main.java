import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static int mayNotWork;

    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static String boardTries(String[] wordsTried, String answer, int j) {
        char[][] arr = new char[j + 1][5];
        int counter = 0;
        int[][] tab = new int[j + 1][5];
        for (int a = 0; a <= j; a++) {
            for (int i = 0; i < 5; i++) {
                arr[a][i] = wordsTried[a].charAt(i);
            }
        }
        for (int i = 0; i <= j; i++) {
            for (int c = 0; c < 5; c++) {
                for (int b = 0; b < 5; b++) {
                    if (answer.charAt(c) == wordsTried[i].charAt(b)) {
                        tab[i][b] = 1;
                    }
                }
            }
        }
        for (int i = 0; i <= j; i++) {
            for (int b = 0; b < 5; b++) {
                if (wordsTried[i].charAt(b) == answer.charAt(b)) {
                    tab[i][b] = 2;
                    counter++;
                }
            }
        }
        printBoard(arr, tab, j);
        if (counter == 5) {
            return answer;
        }
        return null;
    }

    public static void printBoard(String rightWord) {
        for (int b = 0; b < 5; b++) {

            System.out.print(ANSI_GREEN + "\t*---* " + ANSI_RESET);
        }
        System.out.print('\n');
        for (int b = 0; b < 5; b++) {
            System.out.print(ANSI_GREEN + "\t|   | " + ANSI_RESET);
        }
        System.out.print('\n');
        for (int b = 0; b < 5; b++) {

            System.out.print(ANSI_GREEN + "\t| " + rightWord.charAt(b) + " | " + ANSI_RESET);
        }
        System.out.print('\n');
        for (int b = 0; b < 5; b++) {
            System.out.print(ANSI_GREEN + "\t|   | " + ANSI_RESET);
        }
        System.out.print('\n');
        for (int b = 0; b < 5; b++) {
            System.out.print(ANSI_GREEN + "\t*---* " + ANSI_RESET);
        }
        System.out.print("\n\n");
    }

    public static void boardBlank() {
        System.out.println("\t*---* \t*---* \t*---* \t*---* \t*---*");
        System.out.println("\t|   | \t|   | \t|   | \t|   | \t|   |");
        System.out.println("\t|   | \t|   | \t|   | \t|   | \t|   |");
        System.out.println("\t|   | \t|   | \t|   | \t|   | \t|   |");
        System.out.println("\t*---* \t*---* \t*---* \t*---* \t*---*");
    }

    public static void printBoard(char[][] arr, int[][] tab, int j) {
        for (int i = 0; i < j + 1; i++) {
            for (int b = 0; b < 5; b++) {
                if (tab[i][b] == 0) {
                    System.out.print(ANSI_RED + "\t----- " + ANSI_RESET);
                }
                if (tab[i][b] == 1) {
                    System.out.print(ANSI_YELLOW + "\t$$$$$ " + ANSI_RESET);
                }
                if (tab[i][b] == 2) {
                    System.out.print(ANSI_GREEN + "\t+++++ " + ANSI_RESET);
                }
            }
            System.out.print('\n');
            for (int b = 0; b < 5; b++) {
                if (tab[i][b] == 0) {
                    System.out.print(ANSI_RED + "\t-   - " + ANSI_RESET);
                }
                if (tab[i][b] == 1) {
                    System.out.print(ANSI_YELLOW + "\t$   $ " + ANSI_RESET);
                }
                if (tab[i][b] == 2) {
                    System.out.print(ANSI_GREEN + "\t+   + " + ANSI_RESET);
                }
            }
            System.out.print('\n');
            for (int b = 0; b < 5; b++) {
                if (tab[i][b] == 0) {
                    System.out.print(ANSI_RED + "\t- " + arr[i][b] + " - " + ANSI_RESET);
                }
                if (tab[i][b] == 1) {
                    System.out.print(ANSI_YELLOW + "\t$ " + arr[i][b] + " $ " + ANSI_RESET);
                }
                if (tab[i][b] == 2) {
                    System.out.print(ANSI_GREEN + "\t+ " + arr[i][b] + " + " + ANSI_RESET);
                }
            }
            System.out.print('\n');
            for (int b = 0; b < 5; b++) {
                if (tab[i][b] == 0) {
                    System.out.print(ANSI_RED + "\t-   - " + ANSI_RESET);
                }
                if (tab[i][b] == 1) {
                    System.out.print(ANSI_YELLOW + "\t$   $ " + ANSI_RESET);
                }
                if (tab[i][b] == 2) {
                    System.out.print(ANSI_GREEN + "\t+   + " + ANSI_RESET);
                }
            }
            System.out.print('\n');
            for (int b = 0; b < 5; b++) {
                if (tab[i][b] == 0) {
                    System.out.print(ANSI_RED + "\t----- " + ANSI_RESET);
                }
                if (tab[i][b] == 1) {
                    System.out.print(ANSI_YELLOW + "\t$$$$$ " + ANSI_RESET);
                }
                if (tab[i][b] == 2) {
                    System.out.print(ANSI_GREEN + "\t+++++ " + ANSI_RESET);
                }
            }
            System.out.print("\n\n");
        }
    }

    public static boolean isInDictionary(String[] words, String answer) {
        if (Arrays.asList(words).contains(answer))
            return true;
        return false;
    }

    public static int playWORDLE(String[] words, String answer, int level, int howManyTimes) {
        int free = level, tries = 0, forLater = level;
        String[] wordsTried = new String[level];
        Scanner scanner = new Scanner(System.in);
        int j = tries;
        int makeSure = 1;
        while (level > 0) {
            logo();
            for (; j < tries; j++) {
                if (Objects.equals(answer, boardTries(wordsTried, answer, j))) {
                    if (howManyTimes > 1) {
                        level = -1;
                        winner(wordsTried[j]);
                        return level;
                    }
                    winner(wordsTried[j]);
                    return level;
                }
            }
            for (int i = 0; i < free; i++) {
                boardBlank();
            }
            System.out.print("\tTwoja #" + (tries + 1) + " odpowiedz to: ");
            wordsTried[tries] = scanner.nextLine().toLowerCase();
            while (makeSure > 0) {
                if (wordsTried[tries].length() != 5) {
                    System.out.println("Podaj 5-znakowe slowo");
                    wordsTried[tries] = scanner.nextLine().toLowerCase();
                    makeSure++;
                } else {
                    makeSure = 0;
                }
                if (mayNotWork != 0) {
                    if (!(isInDictionary(words, wordsTried[tries]))) {
                        System.out.println("Wyrazu nie ma w słowniku");
                        wordsTried[tries] = scanner.nextLine().toLowerCase();
                        makeSure++;
                    } else {
                        makeSure = 0;
                    }
                }
            }
            makeSure = 1;
            free--;
            tries++;
            level--;
        }
        if (Objects.equals(answer, wordsTried[tries - 1])) {
            winner(wordsTried[tries - 1]);
            return 1;
        } else {
            loser();
            return kontynuuj(words, answer, forLater, howManyTimes);
        }
    }

    public static void winner(String word) {
        logo();
        Scanner scanner = new Scanner(System.in);
        System.out.println("   *         '       *       .  *   '     .           * *");
        System.out.println("                                                               '");
        System.out.println("       *                *'          *          *        '");
        System.out.println("   .           *               |               /");
        System.out.println("               '.         |    |      '       |   '     *");
        System.out.println("                 \\*        \\   \\             /");
        System.out.println("       '          \\     '* |    |  *        |*                *  *");
        System.out.println("            *      `.       \\   |     *     /    *      '");
        System.out.println("  .                  \\      |   \\          /               *");
        System.out.println("     *'  *     '      \\      \\   '.       |");
        System.out.println("        -._            `                  /         *");
        System.out.println("  ' '      ``._   *                           '          .      '");
        System.out.println("   *           *\\*          * .   .      *");
        System.out.println("*  '        *    `-._                       .         _..:='        *");
        System.out.println("             .  '      *       *    *   .       _.:--'");
        System.out.println("          *           .     .     *         .-'         *");
        System.out.println("   .               '             . '   *           *         .");
        System.out.println("  *       ___.-=--..-._     *                '               '");
        System.out.println("                                  *       *");
        System.out.println("                *        _.'  .'       `.        '  *             *");
        System.out.println("     *              *_.-'   .'            `.               *");
        System.out.println("                   .'                       `._             *  '");
        System.out.println("   '       '                        .       .  `.     .");
        System.out.println("       .                      *                  `");
        System.out.println("               *        '             '                          .");
        System.out.println("     .                          *        .           *  *");
        printBoard(word);
        System.out.println("\n\tBrawo wariacie i szacun dla ciebie\n\nNacisnij ENTER by wrócić do Głównego Menu");
        scanner.nextLine();
    }

    public static void loser() {
        logo();
        System.out.println("""
                $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$'               `$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ \s
                $$$$$$$$$$$$$$$$$$$$$$$$$$$$'                   `$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                $$$'`$$$$$$$$$$$$$'`$$$$$$!                       !$$$$$$'`$$$$$$$$$$$$$'`$$$
                $$$$  $$$$$$$$$$$  $$$$$$$                         $$$$$$$  $$$$$$$$$$$  $$$$
                $$$$. `$' \\' \\$`  $$$$$$$!                         !$$$$$$$  '$/ `/ `$' .$$$$
                $$$$$. !\\  i  i .$$$$$$$$                           $$$$$$$$. i  i  /! .$$$$$
                $$$$$$   `--`--.$$$$$$$$$                           $$$$$$$$$.--'--'   $$$$$$
                $$$$$$L        `$$$$$^^$$                           $$^^$$$$$'        J$$$$$$
                $$$$$$$.   .'   ""~   $$$    $.                 .$  $$$   ~""   `.   .$$$$$$$
                $$$$$$$$.  ;      .e$$$$$!    $$.             .$$  !$$$$$e,      ;  .$$$$$$$$
                $$$$$$$$$   `.$$$$$$$$$$$$     $$$.         .$$$   $$$$$$$$$$$$.'   $$$$$$$$$
                $$$$$$$$    .$$$$$$$$$$$$$!     $$`$$$$$$$$'$$    !$$$$$$$$$$$$$.    $$$$$$$$
                $$$$$$$     $$$$$$$$$$$$$$$$.    $    $$    $   .$$$$$$$$$$$$$$$$     $$$$$$$
                                                 $    $$    $
                                                 $.   $$   .$
                                                 `$        $'
                                                  `$$$$$$$$'""");
    }


    public static int kontynuuj(String[] words, String answer, int forLater, int howManyTimes) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chcesz sprobowac jeszcze raz?\n\n\t\t1 - Próbuj dalej\n\t\t2 - Poznaj hasło i przejdz do Głównego Menu");
        while (true) {
            int skan = scanner.nextInt();
            if (skan == 1) {
                return playWORDLE(words, answer, forLater, howManyTimes + 1);
            }
            if (skan == 2) {
                System.out.println("\n\tHasło to: " + answer + "\n\nNacisnij ENTER by wrócic od Głównego Menu");
                String siema = scanner.nextLine();
                return 0;
            } else {
                System.out.println("1 albo 2 Mistrzu!");
            }
        }
    }

    public static int set_difficulty(int level) {
        logo();
        System.out.println("\n\n\tOd poziomu trudnosci zalezy liczba prob przy rozwiazywaniu zagadki\n\t\t1 - latwy(7 prob)\n\t\t2 - normalny(5 prob)\n\t\t3 - trudny(4 proby)\n\n\t\t 4 - wroc");
        Scanner scanner = new Scanner(System.in);
        int numb = scanner.nextInt();
        System.out.println("\n\tCzy chcesz włączyć sprawdzanie czy podany wyraz znajduje się w słowniku? Wiedz że to zmniejszy stabilność gry.\n\t1 - tak\n\t2 - nie");
        int dictionaryChech = scanner.nextInt();
        if (dictionaryChech == 1) {
            mayNotWork = 1;
        } else if (dictionaryChech == 2) {
            mayNotWork = 0;
        } else {
            System.out.println("Podałeś żle, więc uznam że nie chcesz cięższej rozrywki");
            mayNotWork = 0;
        }
        if (numb == 1) {
            return 7;
        }
        if (numb == 2) {
            return 5;
        }
        if (numb == 3) {
            return 4;
        } else {
            return level;
        }
    }

    public static void logo() {
        cls();
        System.out.println(ANSI_BLUE + "\n\tW     WWW     W     OOO    RRR    DDD    L      EEEEE	");
        System.out.println("\tW     W W     W    O   O   R  R   D  D   L      E	");
        System.out.println("\t W   W   W   W    O     O  RRR    D   D  L      EEEE	");
        System.out.println("\t W   W   W   W    O     O  R R    D   D  L      E	");
        System.out.println("\t  W W     W W      O   O   R  R   D  D   L      E");
        System.out.println("\t  WWW     WWW       OOO    R   R  DDD    LLLLL  EEEEE	\n" + ANSI_RESET);

    }

    public static void menu(int winstreak, Players players) {
        logo();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tProfil: " + players.getName());
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tDzisiejszy Winstreak: " + winstreak);
        System.out.println("\n\n\t\t\t\tMenu:\n");
        System.out.println("\t\t\t1. Zagraj w WORDLE\n\t\t\t2. Wybierz poziom trudnosci\n\t\t\t3. Wyniki\n\t\t\t4. Zmien profil\n\t\t\t5. Instrukcja\n\t\t\t6. Zmień platforme\n\t\t\t7. Wyjdz\n");
    }

    public static void showResults(Game game) {
        logo();
        game.print();
        System.out.println("\n\tNacisnij ENTER by kontynuowac");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void instruction() {
        Scanner scanner = new Scanner(System.in);
        logo();
        System.out.println("\n\n\tW grze WORDLE nalezy znaleźć wyraz sposobem \"prób i błęów\".\n\tKolor " + ANSI_RED + "czarwony " + ANSI_RESET + "oznacza, że litery nie ma w wyrazie\n\tKolor " + ANSI_YELLOW + "żółty " + ANSI_RESET + "oznacza, że litera jest w wyrazie na innym polu\n\tKolor " + ANSI_GREEN + "zielony " + ANSI_RESET + "oznacza,że litera jest na swoim miejscu\n\t" + ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "Wiedz że litera może się pojawić więcej niż jeden raz w wyrazie" + ANSI_RESET + "\n\nPamietaj by wybory podejmowac liczbami!\n\nNacisnij ENTER by kontynuować");
        scanner.nextLine();
    }

    public static void changePlatform(String name, int winstreak, int difficultyLevel) throws IOException {
        saveState(name, winstreak, difficultyLevel);
        try {
            new ProcessBuilder("cmd", "/c", "java -jar wordle2.jar").inheritIO().start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void saveState(String name, int winstreak, int difficultyLevel) throws IOException {
        try {
            FileWriter stateOut = new FileWriter("src/state.txt");
            stateOut.write(name + "\n" + winstreak + "\n" + difficultyLevel);
            stateOut.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveState() throws IOException {
        try {
            new FileWriter("src/state.txt", false).close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadState() throws FileNotFoundException {

    }

    public static String selectYourProfile() {
        System.out.println("\n\tProsze wpisz swoje imie: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        cls();
        return name;
    }

    public static void defaultMsg() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\tChyba zle wybrales, byczq\n\tNaciśniej ENTER by kontynuwać");
        //return scanner.nextInt();
        scanner.nextLine();
    }

    public static String[] readWordsFromFile() throws FileNotFoundException {
        File fileIN = new File("src/words.txt");
        Scanner scanner = new Scanner(fileIN);
        int size = scanner.nextInt();
        String aWord;
        char znak;
        String[] words = new String[size];
        for (int i = 0; i < size; i++) {
            words[i] = scanner.nextLine();
        }
        return words;
    }

    public static void main(String[] args) throws java.io.IOException {
        cls();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();


        int choices, check, difficulty_level = 5, score, winstreak = 0, answer, highestScoreThisSession = 0;
        String tmp;
        Players player = null;
        Game game = new Game();
        game.readPlayersFromFile();
        String[] words = readWordsFromFile();
        String name;

        BufferedReader br = new BufferedReader(new FileReader("src/state.txt"));
        if (br.readLine() == null) {
            player = game.logIn();
        } else {
            File stateIN = new File("src/state.txt");
            Scanner skan = new Scanner(stateIN);
            player = game.logIn(skan.nextLine());
            winstreak = skan.nextInt();
            difficulty_level = skan.nextInt();
        }

        cls();
        while (true) {
            assert player != null;
            menu(winstreak, player);
            while (true) {
                tmp = scanner.next();
                if (tmp.equals("1") || tmp.equals("2") || tmp.equals("3") || tmp.equals("4") || tmp.equals("5") || tmp.equals("6") || tmp.equals("7") || tmp.equals("8") || tmp.equals("9") || tmp.equals("0")) {
                    check = Integer.parseInt(tmp);
                    break;
                } else {
                    System.out.println("Podaj cyfre!");
                }
            }
            choices = check;
            switch (choices) {
                case 1 -> {//zagraj w wordle
                    answer = random.nextInt(words.length);
                    System.out.println(answer);
                    System.out.println(words[answer]);
                    score = playWORDLE(words, words[answer], difficulty_level, 1) + 1;
                    System.out.println(score);
                    if (score > 1) {
                        winstreak++;
                    } else {
                        winstreak = 0;
                    }
                    if (winstreak > highestScoreThisSession) {
                        highestScoreThisSession = winstreak;
                    }
                    if (highestScoreThisSession > player.getScore()) {
                        player.setScore(highestScoreThisSession);
                    }
                }
                case 2 ->//wybierz poziom trudnosci
                        difficulty_level = set_difficulty(difficulty_level);
                case 3 ->//wyswietl wyniki
                        showResults(game);
                case 4 -> {//zmien profil
                    logo();
                    System.out.println("Podaj imie profilu: ");
                    scanner.nextLine();
                    name = scanner.nextLine();
                    player = game.logIn(name);
                    if (player == null) {
                        System.out.println("\tNic nie znaleziono, ale stworzylismy profil");
                    } else {
                        System.out.println("Zmieniono na profil o nazwie: " + name);
                    }
                }
                case 5 -> {//instrukcja
                    instruction();
                }
                case 6 -> {//zmiana platformy
                    changePlatform(player.getName(), winstreak, difficulty_level);
                }
                case 7 -> {//wyjscie
                    game.writePlayersToFile();
                    saveState();
                    System.exit(0);
                }
                default -> {
                    defaultMsg();
                    //choices = defaultMsg();
                }
            }
        }
    }
}
