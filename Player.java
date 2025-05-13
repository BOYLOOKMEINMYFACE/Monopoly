public class Player {
    private static int boardSize;
    private String name;
    private int balance;
    private boolean inJail;
    private int position; // Player's current position on the board

    public Player(String name, int initialBalance) {
        this.name = name;
        this.balance = initialBalance;
        this.inJail = false; // Player starts not in jail
        this.position = 0; // Player starts at the "Go" position
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

    public void receiveMoney(int amount) {
        balance += amount; // Add the amount to the player's balance
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
}