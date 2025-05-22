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

    public void executeAction(Player player) {
        super.executeAction(player);
        if (owner == null) {
            // If the property is not owned, ask the player if they want to buy it
            if (player.decideToBuy(cost)) {
                sellProperty(player); // Sell the property to the player
            }
        } else if (owner != player) {
            // If the property is owned by another player, collect rent
            collectRent(player);
        }
    }

    public void collectRent(Player player){
        player.payMoney(owner, getRent()); // Deduct rent from the player
        if (player.getBalance() != -1){
            owner.receiveMoney(getRent()); // Add rent to the owner's balance
        }
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

    public void setOwner(Player owner) {
        this.owner = owner; // Set the owner of the property
    }

    public void sellProperty(Player player) {
        if (owner == null && player.buyProperty(cost)) {
            owner = player; // Set the owner to the player who bought the property
            System.out.println(player.getName() + " bought " + getName() + " for $" + cost);
        }
    }

}