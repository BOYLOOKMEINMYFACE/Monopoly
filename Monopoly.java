public class Monopoly {
    public static void main(String[] args) {
        // Create a new game with 4 players
        Game game = new Game(4);
        
        // Example of player actions
        Player player1 = game.getPlayers().get(0);
        player1.buyProperty(200);
        
        // Example of bank action
        Bank bank = new Bank();
        int newBalance = bank.passGo(player1.getBalance());
        
        // End the game
        game.end();
    }
}