import java.util.ArrayList;

public class Utility extends Property {
    // List to keep track of all utilities to calculate rent
    private static ArrayList<Utility> utilities = new ArrayList<Utility>();

    public Utility(String name, int cost) {
        super(name, 150);
        utilities.add(this);
    }

    public int getRent(Game game) {
        Player owner = this.getOwner();
        int numUtilitiesOwned = (int) utilities.stream()
            .filter(utility -> utility.getOwner() == owner)
            .count();
        int rentMultiplier = (numUtilitiesOwned == 1) ? 4 : 10;
        int totalDiceRoll = game.getDices()[0].roll() + game.getDices()[1].roll();
        return  rentMultiplier * totalDiceRoll;
    }

}
