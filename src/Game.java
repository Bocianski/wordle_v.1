import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final ArrayList<Players> playersList = new ArrayList<>();

    public void readPlayersFromFile() throws FileNotFoundException {
        File fileIN = new File("src/players.txt");
        Scanner scanner = new Scanner(fileIN);
        int size = scanner.nextInt();
        String name;
        int score;
        for (int i = 0; i < size; i++) {
            score = scanner.nextInt();
            name = scanner.next();
            addPlayer(new Players(name, score));
        }
    }

    public void writePlayersToFile() {
        try {
            FileWriter fileOut = new FileWriter("src/players.txt");
            fileOut.write(playersList.size() + "\n");
            for (Players players : playersList) {
                fileOut.write(players.getScore() + " " + players.getName() + "\n");
            }
            fileOut.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPlayer(Players player) {
        playersList.add(player);
    }

    public void print() {
        for (Players players : playersList) {
            System.out.println("\t\t" + players.getName() + ":\t" + players.getScore());
        }
    }

    public Players searchPlayer(String name) {
        for (Players players : playersList) {
            if (name.equals(players.getName())) {
                return players;
            }
        }
        return null;
    }

    public Players logIn() {
        System.out.println("\n\t Witaj w: \n");
        Main.logo();
        String name = Main.selectYourProfile();
        Players player;
        player = searchPlayer(name);
        if (player == null) {
            addPlayer(player = new Players(name));
            Main.instruction();
            return player;
        }
        return player;
    }

    public Players logIn(String name) {
        Players player;
        player = searchPlayer(name);
        Main.cls();
        if (player == null) {
            addPlayer(player = new Players(name));
            return player;
        }
        return player;
    }
}
