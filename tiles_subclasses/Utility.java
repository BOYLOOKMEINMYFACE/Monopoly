import java.util.ArrayList;

public class Utility extends Property {
    // List to keep track of all utilities to calculate rent
    private static ArrayList<Utility> utilities = new ArrayList<Utility>();
    private Dice dice1;
    private Dice dice2;

    public Utility(String name, int cost) {
        super(name, 150);
        utilities.add(this);
        this.dice1 = new Dice(6);
        this.dice2 = new Dice(6);
    }

    public int getRent() {
        Player owner = this.getOwner();
        int numUtilitiesOwned = (int) utilities.stream()
            .filter(utility -> utility.getOwner() == owner)
            .count();
        int rentMultiplier = (numUtilitiesOwned == 1) ? 4 : 10;
        int totalDiceRoll = this.dice1.roll() + this.dice2.roll();
        return  rentMultiplier * totalDiceRoll;
    }
    
}
