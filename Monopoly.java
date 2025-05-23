import java.util.ArrayList;
import java.util.Scanner;

public class Monopoly {
    public static void main(String[] args) {

        // Simple 4 player bots game
        Game game = new Game(4);
        game.play();

        // Game with human and 3 bots
        // ArrayList<Player> players = new ArrayList<>();
        // players.add(new Human("JZ"));
        // for (int i = 1; i < 4; i++) {
        //     players.add(new Player("P" + i));
        // }
        // Game game = new Game(players);

        // This is setting all properties to be owned by JZ
        // for (Tiles tile : game.getBoard()) {
        //     if (tile instanceof Property) {
        //         ((Property) tile).setOwner(players.get(0)); // players.get(0) is "JZ"
        //     }
        // }

        // game.play();

        // Play 10 games with the same players
        // ArrayList<Player> players = new ArrayList<>();
        // for (int i = 0; i < 4; i++) {
        // players.add(new Player("P" + (i + 1)));
        // }
        // playMultipleGames(players, 10);

        // Scanner sc = new Scanner(System.in);
        // Game game = new Game(getPlayers(sc));
        // game.play();
    }

    public static ArrayList<Player> getPlayers(Scanner sc) {
        System.out.println("Enter the number of players (2-6): ");
        int numPlayers = sc.nextInt();
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter the name of player " + (i + 1) + ": ");
            String name = sc.next();
            players.add(new Player(name));
        }
        return players;
    }

    public static void playMultipleGames(ArrayList<Player> players, int numGames) {
        int[] wins = new int[players.size()];
        for (int i = 0; i < numGames; i++) {
            Game game = new Game(players);
            game.play();
            for (int j = 0; j < players.size(); j++) {
                if (game.getWinner() == players.get(j)) {
                    wins[j]++;
                }
            }
            game.resetGame();
        }
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getName() + " won " + wins[i] + " times.");
        }
    }
}
