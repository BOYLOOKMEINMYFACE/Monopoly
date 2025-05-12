import java.util.ArrayList;

public class Jail extends Tiles {
    private static final int JAIL_TURN_LIMIT = 3; // Maximum turns in jail
    private static ArrayList<Player> playersInJail = new ArrayList<>();
    private int jailTile;

    public Jail(int jailTile) {
        super("Jail");
        this.jailTile = jailTile;
    }

    public ArrayList<Player> getPlayersInJail() {
        return playersInJail;
    }

    public void addPlayerToJail(Player player) {
        if (!playersInJail.contains(player)) {
            playersInJail.add(player);
            player.setInJail(true);
        }
    }
    
    public void releaseFromJail(Player player) {
        if (playersInJail.contains(player)) {
            playersInJail.remove(player);
            player.setInJail(false);
        }
    }

    public int getJailTile() {
        return jailTile;
    }
}