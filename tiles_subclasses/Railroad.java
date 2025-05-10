import java.util.ArrayList;

public class Railroad extends Property {
    // List to keep track of all railroads to calculate rent
    public static ArrayList<Railroad> railroads = new ArrayList<Railroad>();

    public Railroad(String name, int cost) {
        super(name, cost);
        railroads.add(this);
    }
    
    public int getRent() {
        Player owner = this.getOwner();
        int numRailroadsOwned = (int) railroads.stream()
            .filter(railroad -> railroad.getOwner() == owner)
            .count();
        return 25 * numRailroadsOwned;
    }   
}