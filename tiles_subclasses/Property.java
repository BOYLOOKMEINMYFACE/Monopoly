public class Property extends Tiles {
    private int cost;
    private int rent;
    private Player owner;

    public Property(String name, int cost, int rent) {
        super(name);
        this.cost = cost;
        this.rent = rent;
        this.owner = null; // No owner initially
    }

    public Property(String name, int cost) {
        super(name);
        this.cost = cost;
        this.rent = 0; // Default rent to 0 if not specified
        this.owner = null; // No owner initially
    }

    public int getCost() {
        return cost;
    }

    public int getRent() {
        return rent;
    }

    public Player getOwner() {
        return owner;
    }

    public void sellProperty(Player player) {
        if (owner == null && player.buyProperty(cost)) {
            owner = player; // Set the owner to the player who bought the property
        }
    }

}