public class RealEstate extends Property {
    private static final int MAX_HOUSES = 5; // Maximum number of houses allowed
    private int[] rents;
    private int costOfHouse;
    private int costOfHotel;
    private int numHouses;
    private int group; // Group to which the property belongs
    private boolean isMonopoly; // Flag to check if the player owns all properties in the group

    public RealEstate(String name, int cost, int[] rents, int costOfHouse, int group) {
        super(name, cost);  // Call the parent class constructor
        this.rents = rents;
        this.costOfHouse = costOfHouse; // Set the cost of building a house
        this.costOfHotel = costOfHouse * 4 + 150; // Set the cost of building a hotel
        this.numHouses = 0; // Initialize the number of houses to 0
        this.group = group; // Set the group to which the property belongs
        this.isMonopoly = false; // Initialize the monopoly flag to false
    }

    public void executeAction(Player player) {
        if (getOwner() == player && isMonopoly) {
            buildHouseAction(player);
        } else {
            if (isMonopoly) {
                System.out.println(player.getName() + " has a monopoly on this tile.");
            }
            super.executeAction(player); // Call the parent class's executeAction method
        }
    }

    public void buildHouseAction(Player player) {
        System.out.println(player.getName() + " can build a house on " + getName());
        System.out.println("Current number of houses: " + numHouses);
        System.out.println("Cost of house: " + getCostOfHouse());
        // If the property is owned by the player, they can build houses
        if (player.decideToBuildHouse()) {
            System.out.println(player.getName() + " is building a house on " + getName());
            buildHouse();
        }
    }

    public void buildHouse() {
        if (numHouses < MAX_HOUSES) { // Check if there is room for more houses
            numHouses++;
            getOwner().buyHouse(getCostOfHouse()); // Deduct the cost of building a house from the owner's balance
        } else {
            System.out.println("Maximum number of houses reached.");
        }
    }

    public void setMonopoly(boolean isMonopoly) {
        this.isMonopoly = isMonopoly; // Set the monopoly status
    }

    public int getRent() {
        return (isMonopoly && numHouses == 0) ? rents[0] * 2 : rents[numHouses]; // Return the rent based on the number of houses
    }

    public int getCostOfHouse() {
        return (numHouses < 4) ? costOfHouse : costOfHotel; // Return the cost of building a house
    }

    public int getGroup() {
        return group; // Return the group to which the property belongs
    }

    public String getName() {
        return super.getName() + " (" + group + ")"; // Return the name of the property
    }
}