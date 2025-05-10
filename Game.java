import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Game {

    private Dice dice1;
    private Dice dice2;
    private ArrayList<Player> players;
    private ArrayList<Tiles> board;

    // Constructor
    public Game(int numberOfPlayers) {

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
            br.readLine(); // Skip the first line (header)
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[1].trim();
                String type = parts[2].trim();
                int cost = Integer.parseInt(parts[3].trim());

                board.add(switch (type) {
                    case "RealEstate" -> {
                        int[] rents = parseRents(parts, 4, 5);
                        yield new RealEstate(name, cost, rents, 100); // adding a dummy value for cost of house
                    }
                    case "Railroad" -> new Railroad(name, cost);
                    case "Utility" -> new Utility(name, cost);
                    case "Chances" -> new Chances();
                    case "CommunityChest" -> new CommunityChest();
                    case "LuxuryTax" -> new LuxuryTax();
                    case "IncomeTax" -> new IncomeTax();
                    case "Go" -> new Go();
                    case "Jail" -> new Jail();
                    case "FreeParking" -> new FreeParking();
                    case "GoToJail" -> new GoToJail();
                    default -> throw new IllegalArgumentException("Unknown tile type: " + type);
                });
            }
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Failed to load board from CSV file.", e);
        }
        return board;
    }

    private int[] parseRents(String[] parts, int startIndex, int count) {
        int[] rents = new int[count];
        for (int i = 0; i < count; i++) {
            rents[i] = Integer.parseInt(parts[startIndex + i].trim());
        }
        return rents;
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