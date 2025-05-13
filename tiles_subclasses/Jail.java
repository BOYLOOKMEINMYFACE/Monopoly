import java.util.ArrayList;

public class Jail extends Tiles {
    private static final int JAIL_TURN_LIMIT = 3; // Maximum turns in jail
    private static ArrayList<Player> playersInJail = new ArrayList<>();
    private static ArrayList<Integer> turnCount = new ArrayList<>();
    private int jailTile;

    public Jail(int jailTile) {
        super("Jail");
        this.jailTile = jailTile;
    }

    public ArrayList<Player> getPlayersInJail() {
        return playersInJail;
    }
    
    public void executeAction(Player player) {
        if(player.getInJail()) {
            arrestedAction(player);
        } else {
            System.out.println(player.getName() + " is not in jail. He is passing by");
        }
    }

    public void arrestedAction(Player player) {
        System.out.println(player.getName() + " is in jail and cannot take a turn.");
        int index = playersInJail.indexOf(player);
        if(turnCount.get(index) < JAIL_TURN_LIMIT) {
            turnCount.set(index, turnCount.get(index) + 1);
            System.out.println(player.getName() + " has been in jail for " + turnCount.get(index) + " turns.");
        } else {
            releaseFromJail(player);
            System.out.println(player.getName() + " has been released from jail after " + JAIL_TURN_LIMIT + " turns.");
        }
    }

    public void addPlayerToJail(Player player) {
        if (!playersInJail.contains(player)) {
            turnCount.add(0);
            playersInJail.add(player);
            player.setInJail(true);
        }
    }
    
    public void releaseFromJail(Player player) {
        if (playersInJail.contains(player)) {
            turnCount.remove(playersInJail.indexOf(player));
            playersInJail.remove(player);
            player.setInJail(false);
        }
    }

    public int getJailTile() {
        return jailTile;
    }

    public void setJailTile(int jailTile) {
        this.jailTile = jailTile;
    }
}