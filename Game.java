import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Game {

    private Dice dice1;
    private Dice dice2;
    private ArrayList<Player> players;
    private Bank bank;
    private ArrayList<Tiles> board;

    // Constructor
    public Game(int numberOfPlayers) {
        this.bank = new Bank();

        this.players = new ArrayList<>();
        if (numberOfPlayers < 2 || numberOfPlayers > 6) {
            throw new IllegalArgumentException("Number of players must be between 2 and 6.");
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player("Player " + (i + 1), 1500));
        }

        this.dice1 = new Dice(6);
        this.dice2 = new Dice(6);

        board = initiateBoard();
    }

    public ArrayList<Tiles> initiateBoard() {
        ArrayList<Tiles> board = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("tiles_subclasses/monopolyTiles.csv"))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the first line
                }
                String[] parts = line.split(",");
                String name = parts[1].trim(); // Adjusted to use the correct column for the name
                board.add(new Tiles(name));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load board from CSV file.");
        }
        return board;
    }

    public Dice[] getDices() {
        return new Dice[] { dice1, dice2 };
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Tiles> getBoard() {
        return board;
    }
}