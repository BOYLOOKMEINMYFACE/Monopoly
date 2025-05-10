public class FreeParking extends Tiles {
    private int balance;

    public FreeParking() {
        super("Free Parking");
        this.balance = 0; // Initialize balance to 0
    }

    public int getbalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
}
