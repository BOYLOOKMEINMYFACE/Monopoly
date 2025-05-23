import java.util.ArrayList;

public class Railroad extends Property {
    // List to keep track of all railroads to calculate rent
    public static ArrayList<Railroad> railroads = new ArrayList<Railroad>();
    public static final int[] rents = { 25, 50, 100, 200 }; // Rent values for 1 to 4 railroads

    public Railroad(String name, int cost) {
        super(name, cost);
        railroads.add(this);
    }

    public int getRent() {
        Player owner = this.getOwner();
        int numRailroadsOwned = (int) railroads.stream()
                .filter(railroad -> railroad.getOwner() == owner)
                .count();
        return rents[numRailroadsOwned - 1]; // Rent based on number of railroads owned
    }

    public void resetTile() {
        super.resetTile();
        railroads.clear(); // Clear the list of railroads
    }
}