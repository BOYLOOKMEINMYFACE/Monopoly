public class Railroad extends Property {
    public static ArrayList<Railroad> railroads = new ArrayList<Railroad>();

    public Railroad(String name, int cost) {
        super(name, 200);
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