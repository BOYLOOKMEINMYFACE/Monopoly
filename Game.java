import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Game {

    private Dice dice1;
    private Dice dice2;
    private ArrayList<Player> players;
    private ArrayList<Tiles> board;

    private Jail jail;

    // Constructor
    public Game(int numberOfPlayers) {

        Player.setBoardSize(40); // Set the board size for all players

        this.players = new ArrayList<>();
        if (numberOfPlayers < 2 || numberOfPlayers > 6) {
            throw new IllegalArgumentException("Number of players must be between 2 and 6.");
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player("P" + (i + 1), 1500));
        }

        this.dice1 = new Dice(6);
        this.dice2 = new Dice(6);

        board = initializeBoard();
        linkJailTiles();

    }

    private void linkJailTiles() {
        for (Tiles tile : board) {
            if (tile instanceof Jail) {
                this.jail = (Jail) tile;
                this.jail.setJailTile(board.indexOf(tile));
                for (Tiles t : board) {
                    if (t instanceof GoToJail) {
                        ((GoToJail) t).setJail(((Jail) tile));
                    }
                }
            }
        }
    }

    private ArrayList<Tiles> initializeBoard() {
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
                        int[] rents = parseRents(parts, 4, 6);
                        int costOfHouse = Integer.parseInt(parts[10].trim());
                        int group = Integer.parseInt(parts[11].trim());
                        yield new RealEstate(name, cost, rents, costOfHouse, group);
                    }
                    case "Railroad" -> new Railroad(name, cost);
                    case "Utility" -> new Utility(name, cost);
                    case "Chances" -> new Chances("Chances");
                    case "CommunityChest" -> new CommunityChest("Community Chest");
                    case "LuxuryTax" -> new LuxuryTax();
                    case "IncomeTax" -> new IncomeTax();
                    case "Go" -> new Tiles("Go");
                    case "Jail" -> new Jail(board.size());
                    case "FreeParking" -> new Tiles("Free Parking");
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

    public void play() {
        initialSetUp();
        while (!checkEnd()) {
            playOneRound();
        }
        broadCastGameEnd(getWinner());

    }

    public void initialSetUp() {
        System.out.println("Welcome to Monopoly!");
        System.out.println("Game initialized with " + players.size() + " players.");
        broadCastBoard();
    }

    public void broadCastBoard() {
        System.out.println("Monopoly Board:");
        for (int i = 0; i < board.size(); i++) {
            Tiles tile = board.get(i);
            StringBuilder playersOnTile = new StringBuilder();
            for (Player player : players) {
                if (player.getPosition() == i) {
                    playersOnTile.append("[").append(player.getName()).append("] ").append(" ");
                }
            }
            String owner = "";
            if (tile instanceof Property) {
                Player tileOwner = ((Property) tile).getOwner();
                owner = (tileOwner != null) ? tileOwner.getName() : "";
            }
            System.out.printf("[%2d] %-25s | Owner: %-4s | %s%n",
                    i + 1, tile.getName(), owner, playersOnTile.toString().trim());
        }
    }

    public void broadCastBalance() {
        System.out.println("Player Balances:");
        for (Player player : players) {
            if (player.getBalance() < 0) {
                System.out.printf("%s is bankrupt!%n", player);
            } else {
                System.out.printf("%s balance: $%d%n", player, player.getBalance());
            }
        }
        System.out.println("____________________________________________________________");
    }

    public void playOneRound() {
        System.out.println("============================================================");
        System.out.println("New Round!");
        broadCastBalance();
        for (Player player : players) {
            if (!player.getInJail()) {
                takeTurn(player);
            } else {
                jail.executeAction(player);
            }
            System.out.println("____________________________________________________________");
        }
        broadCastBoard();

    }

    public void takeTurn(Player player) {
        // roll the dices first
        int rollOne = dice1.roll();
        int rollTwo = dice2.roll();
        System.out.println(player + " rolled a " + rollOne + " and " + rollTwo);

        checkMonopoly(); // Check for monopolies of properties at the start of each turn

        if (checkJail(rollOne, rollTwo)) { // check if the player is going to jail
            sendToJail(player);
        } else { // if not in jail, move the playere
            executeTurn(player, rollOne, rollTwo);
            if (rollOne == rollTwo) {
                System.out.println(player + " rolled doubles! Roll again.");
                takeTurn(player); // Allow the player to roll again
            }
        }
    }

    private void executeTurn(Player player, int rollOne, int rollTwo) {
        if (player.move(rollOne + rollTwo)) {
            System.out.println(player + " passed GO and collected $200!");
            player.receiveMoney(200);
        }
        Tiles currentTile = board.get(player.getPosition());
        currentTile.executeAction(player);
    }

    private int count = 0;

    public boolean checkEnd() {
        for (Player player : players) {
            if (player.getBalance() <= 0) {
                System.out.println(player + " is bankrupt!");
                players.remove(player);
                return players.size() == 1; // Game ends if only one player remains
            }
        }
        return count++ > 25 || false; // Game continues
    }

    public Player getWinner() {
        Player richest = players.get(0);
        for (Player player : players) {
            if (player.getBalance() > richest.getBalance()) {
                richest = player;
            }
        }
        return richest;
    }

    public void broadCastGameEnd(Player winner) {
        System.out.println("============================================================");
        System.out.println("Game Over!");
        System.out.println(
                winner + " has won the game with a balance of $" +
                        winner.getBalance() + "!");
    }

    public void checkMonopoly() {
        // Map from group number to list of RealEstate in that group
        Map<Integer, List<RealEstate>> groupMap = new HashMap<>();
        for (Tiles tile : board) {
            if (tile instanceof RealEstate property) {
                groupMap.computeIfAbsent(property.getGroup(), k -> new ArrayList<>()).add(property);
            }
        }

        // For each group, check if all properties are owned by the same player
        for (List<RealEstate> groupProps : groupMap.values()) {
            Player owner = groupProps.get(0).getOwner();
            boolean hasMonopoly = owner != null;
            for (RealEstate prop : groupProps) {
                if (prop.getOwner() != owner || owner == null) {
                    hasMonopoly = false;
                    break;
                }
            }
            for (RealEstate prop : groupProps) {
                prop.setMonopoly(hasMonopoly);
            }
        }
    }

    private void sendToJail(Player player) {
        System.out.println(player + " has been sent to jail for rolling two 1s!");
        player.setPosition(jail.getJailTile());
        jail.addPlayerToJail(player);
    }

    private boolean checkJail(int rollOne, int rollTwo) {
        return (rollOne == rollTwo && rollOne == 1);
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