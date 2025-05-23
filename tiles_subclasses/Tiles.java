public class Tiles{
    private String name;

    public Tiles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public void executeAction(Player player) {
        // Default action for a tile, can be overridden by subclasses
        System.out.println(player.getName() + " landed on " + name);
    }

    public void resetTile() {
        // Default reset action for a tile, can be overridden by subclasses
        System.out.println(name + " has been reset.");
    }
}