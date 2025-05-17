public class Monopoly {
    public static void main(String[] args) {
        Game game = new Game(4); // Example with 4 players
        // Set all RealEstate owners to player 1 (index 0)
        for (Tiles square : game.getBoard()) {
            if (square instanceof RealEstate) {
            ((RealEstate) square).setOwner(game.getPlayers().get(0));
            }
        }
        game.play();
    }
}