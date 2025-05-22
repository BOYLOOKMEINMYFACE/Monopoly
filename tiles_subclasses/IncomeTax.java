import java.util.ArrayList;

public class IncomeTax extends Tiles {
    private final int LUMPSUM_INCOME_TAX_AMOUNT = 200; // Amount to pay for income tax
    private ArrayList<Tiles> board;
    private final int INCOME_TAX_PERCENTAGE = 10; // Percentage of income tax

    public IncomeTax() {
        super("Income Tax");
    }

    public void executeAction(Player player) {
        super.executeAction(player);
        int totalPropertyValue = 0;
        for (Tiles tile : board) {
            if (tile instanceof Property) {
                Property property = (Property) tile;
                if (property.getOwner() == player) {
                    totalPropertyValue += property.getCost();
                }
            }
        }
        int tenPercent = totalPropertyValue / INCOME_TAX_PERCENTAGE;
        int taxToPay = Math.min(LUMPSUM_INCOME_TAX_AMOUNT, tenPercent);
        player.payMoney(taxToPay);
    }

    public void setBoard(ArrayList<Tiles> board) {
        this.board = board;
    }
}