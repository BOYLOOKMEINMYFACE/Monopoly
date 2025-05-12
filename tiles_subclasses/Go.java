public class Go extends Tiles {

    public Go() {
        super("GO");
    }

    public void collectMoney(Player player) {
        int amount = 200;
        player.receiveMoney(amount);
        System.out.println(player.getName() + " collected " + amount + " from" + super.getName());
    }

    public void executeAction(Player player) {
        super.executeAction(player);
        collectMoney(player);
    }
}