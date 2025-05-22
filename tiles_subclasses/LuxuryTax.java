public class LuxuryTax extends Tiles {
    private static final int LUXURY_TAX_AMOUNT = 75; // Amount to pay for luxury tax

    public LuxuryTax() {
        super("Luxury Tax");
    }

    public void executeAction(Player player) {
        super.executeAction(player);
        System.out.println(player.getName() + " pays $" + LUXURY_TAX_AMOUNT + " as luxury tax.");
        player.payMoney(LUXURY_TAX_AMOUNT); // Deduct the luxury tax amount from the player's balance
    }
}