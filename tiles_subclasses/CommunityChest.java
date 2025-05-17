public class CommunityChest extends Tiles {

    private String[] messages;
    private int[] effects;

    public CommunityChest() {
        super("Community Chest");
        this.messages = setUpMessages();
        this.effects = setUpEffects();
    }
    
    private String[] setUpMessages() {
        return new String[]{
            "Advance to Go (Collect $200)",
            "Bank error in your favor. Collect $200",
            "Doctor's fee. Pay $50",
            "From sale of stock you get $50",
            "Go to Jail. Go directly to Jail. Do not pass Go. Do not collect $200",
            "Holiday fund matures. Receive $100",
            "Income tax refund. Collect $20",
            "It is your birthday. Collect $10 from each player",
            "Life insurance matures. Collect $100",
            "Pay hospital fees of $100",
            "Pay school fees of $50",
            "Receive $25 consultancy fee",
            "You are assessed for street repairs. Pay $40 per house and $115 per hotel you own.",
            "You have won second prize in a beauty contest. Collect $10"
        };
    }

    private int[] setUpEffects() {
        return new int[]{
            200, 200, -50, 50, 0, 100, 20, 0, 100,
            -100, -50, 25, -40, -115, 10
        };
    }

    public void executeAction(Player player) {
        super.executeAction(player);
        int randomIndex = (int) (Math.random() * messages.length);
        System.out.println(messages[randomIndex]);
        int effect = effects[randomIndex];
        if (effect > 0) {
            player.receiveMoney(effect);
        } else if (effect < 0) {
            player.payMoney(-effect);
        }
    }
}
