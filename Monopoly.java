import java.util.ArrayList;
import java.util.Scanner;

public class Monopoly {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        Game game = new Game(getPlayers(sc));
        game.play();
    }

    public static ArrayList<Player> getPlayers(Scanner sc){
        System.out.println("Enter the number of players (2-6): ");
        int numPlayers = sc.nextInt();
        ArrayList<Player> players = new ArrayList<>();
        for(int i = 0; i < numPlayers; i++) {
            System.out.println("Enter the name of player " + (i + 1) + ": ");
            String name = sc.next();
            players.add(new Player(name));
        }
        return players;
    }
}
