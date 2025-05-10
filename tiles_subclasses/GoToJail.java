public class GoToJail extends Tiles {

    public GoToJail() {
        super("Go to Jail");
    }

    public void arrested(Player player) {
        System.out.println(player.getName() + " has been arrested and sent to jail!");
        //player.setPosition(10); // Assuming jail is at index 10
        player.setInJail(true);
    }
}