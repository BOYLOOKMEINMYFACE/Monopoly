public class RealEstate extends Property {
    private static final int MAX_HOUSES = 5; // Maximum number of houses allowed
    private int[] rents;
    int costOfHouse;
    int costOfHotel;
    int numHouses;

    public RealEstate(String name, int cost, int[] rents, int costOfHouse) {
        super(name, cost, rents[0]); // Set the base rent
        this.rents = rents;
        this.costOfHouse = costOfHouse; // Set the cost of building a house
        this.costOfHotel = costOfHouse * 4 + 150; // Set the cost of building a hotel
        this.numHouses = 0; // Initialize the number of houses to 0
    }

    public void buildHouse() {
        if (numHouses < rents.length - 1) { // Check if there is room for more houses
            numHouses++;
            super.getOwner().buyHouse(getCostOfHouse()); // Deduct the cost of building a house from the owner's balance
        } else {
            System.out.println("Maximum number of houses reached.");
        }
    }

    public int getRent() {
        return rents[numHouses]; // Return the rent based on the number of houses
    }

    public int getCostOfHouse() {
        return costOfHouse; // Return the cost of building a house
    }
}