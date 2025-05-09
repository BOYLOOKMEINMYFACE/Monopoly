public class Player {
    private String name;
    private int balance;

    public Player(String name, int initialBalance) {
        this.name = name;
        this.balance = initialBalance;
    }
    
    public String getName() {
        return name;
    }
    
    public int getBalance() {
        return balance;
    }

    public boolean buyProperty(int propertyCost) {
        if (balance >= propertyCost) {
            balance -= propertyCost;
            return true; // Purchase successful
        } else {
            return false; // Not enough balance
        }
    }
    
}