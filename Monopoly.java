import java.util.ArrayList;

public class Monopoly {
    public static void main(String[] args) {
        Game game = new Game(4); // Example with 4 players
        game.broadCastBoard();
        game.play();
    }
}