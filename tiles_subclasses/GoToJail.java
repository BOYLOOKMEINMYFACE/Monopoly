public class GoToJail extends Tiles {
    private int jailTile;

    public GoToJail() {
        super("Go to Jail");
    }

    public void arrested(Player player) {
        System.out.println(player.getName() + " has been arrested and sent to jail!");
        player.setPosition(jailTile);
        player.setInJail(true);
    }

    public void executeAction(Player player) {
        super.executeAction(player);
        arrested(player);
    }

    public void setJailTile(int jailTile) {
        this.jailTile = jailTile;
    }
}