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

        this.board = new ArrayList<>();
    }

    public ArrayList<Tiles> initiateBoard() {
        try (BufferedReader br = new BufferedReader(new FileReader("board.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            String name = parts[0];
            int cost = Integer.parseInt(parts[1]);
            board.add(new Tiles(name, cost));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return board;
    }

    public Dice[] getDices() {
        return new Dice[]{dice1, dice2};
    }
}