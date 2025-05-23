public class Player {
    private static int boardSize;
    private String name;
    private int balance;
    private boolean inJail;
    private int position; // Player's current position on the board
    private int propertyOwned;

    public Player(String name) {
        this.name = name;
        this.balance = 1500; // Starting balance
        this.inJail = false; // Player starts not in jail
        this.position = 0; // Player starts at the "Go" position
        this.propertyOwned = 0; // Number of properties owned
    } 

    public boolean decideToBuy(int propertyCost) {
        // Logic to decide whether to buy a property

        if (propertyOwned <= 5) {
            return true;
        }
        // The lower the property cost, the higher the chance to buy
        double probability = Math.max(0.1, Math.min(1.0, 1.0 - ((propertyCost - 200) / 400.0))); // $200 = 100%, $600 = 0%
        return Math.random() < probability;
    }

    public boolean buyProperty(int propertyCost) {
        if (balance >= propertyCost) {
            propertyOwned++; // Increment the number of properties owned
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

    public void payMoney(Player receiver, int amount) {
        if (balance >= amount) {
            balance -= amount; // Deduct the amount from the player's balance
            System.out.println(name + " paid $" + amount + " to " + receiver.getName());
        } else {
            System.out.println("Not enough balance to pay.");
            balance = -1; // Set balance to -1 to indicate bankruptcy
        }

    }
    
    // Overloaded method to pay money without a receiver
    public void payMoney(int amount) {
        if (balance >= amount) {
            balance -= amount; // Deduct the amount from the player's balance
            System.out.println(name + " paid $" + amount);
        } else {
            System.out.println("Not enough balance to pay.");
            balance = -1; // Set balance to -1 to indicate bankruptcy
        }
    }

    public void receiveMoney(int amount) {
        balance += amount; // Add the amount to the player's balance
        System.out.println(name + " received $" + amount);
    }

    public int getPropertyOwned() {
        return propertyOwned;
    }

    public boolean getInJail() {
        return inJail;
    }

    public String getName() {
        return name;
    }
    
    public int getBalance() {
        return balance;
    }

    public void setInJail(boolean status) {
        inJail = status;
    }

    public int getPosition() {
        return position;
    }
    
    public boolean move(int spaces) {
        boolean passedGo = position + spaces >= boardSize;
        position = (position + spaces) % boardSize; // Move the player forward on the board
        return passedGo;
    }
    
    public void setPosition(int position) {
        this.position = position; 
    }

    public static void setBoardSize(int size) {
        boardSize = size;
    }

    public String toString() {
        return name;
    }

    public void resetPlayer() {
        balance = 1500; // Reset balance to starting amount
        inJail = false; // Reset jail status
        position = 0; // Reset position to "Go"
        propertyOwned = 0; // Reset number of properties owned
    }
}