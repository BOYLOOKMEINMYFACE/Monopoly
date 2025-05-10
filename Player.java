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
    
    public void buyHouse(int cost) {
        if (balance >= cost) {
            balance -= cost; // Deduct the cost of building a house
        } else {
            System.out.println("Not enough balance to build a house.");
        }
    }

    public void payRent(int rent) {
        if (balance >= rent) {
            balance -= rent; // Deduct the rent from the player's balance
        } else {
            System.out.println("Not enough balance to pay rent.");
        }
    }

}