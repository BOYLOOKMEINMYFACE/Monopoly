public class GoToJail extends Tiles {
    private Jail jail;

    public GoToJail() {
        super("Go to Jail");
    }

    public void arrested(Player player) {
        System.out.println(player.getName() + " has been arrested and sent to jail!");
        player.setPosition(jail.getJailTile());
        player.setInJail(true);
        jail.addPlayerToJail(player);
    }

    public void executeAction(Player player) {
        super.executeAction(player);
        arrested(player);
    }

    public void setJail(Jail jail) {
        this.jail = jail;
    }
}