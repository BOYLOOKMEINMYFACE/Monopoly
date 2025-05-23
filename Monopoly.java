import java.util.ArrayList;
import java.util.Scanner;

public class Monopoly {
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new Player("P" + (i + 1)));
        }

        int[] wins = new int[4];
        for (int i = 0; i < 10; i++){
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
}
