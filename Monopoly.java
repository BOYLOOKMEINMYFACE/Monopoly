import java.util.ArrayList;

public class Monopoly {
    public static void main(String[] args) {
        Game game = new Game(4); // Example with 4 players
        System.out.println("Game initialized with " + game.getPlayers().size() + " players.");
        game.broadCastBoard();
        // ArrayList<Tiles> board = game.getBoard();
        // for (Tiles tile : board) {
        //     System.out.println("Tile: " + tile.getName());
        // }
    }
}