public class RealEstate extends Property {

    public RealEstate(String name, int cost, int[] rents) {
        super(name, cost, rents[0]); // Set the base rent
    }

    public int getRent() {
        return super.getRent();
    }
}