
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Chances extends Tiles {

    private ArrayList<String> messages;
    private ArrayList<String> destinations;
    private ArrayList<Tiles> board;

    public Chances(String name) {
        super(name);
        this.messages = setUpMessages(0);
        this.destinations = setUpMessages(1);
    }

    public void executeAction(Player player) {
        super.executeAction(player);
        int randomIndex = (int) (Math.random() * messages.size());
        System.out.println(messages.get(randomIndex));
        int destination = findPosition(destinations.get(randomIndex));
        System.out.println("Moving to " + destinations.get(randomIndex));
        int moveDistance = (destination - player.getPosition() + board.size()) % board.size();
        executeTileAction(player, moveDistance);
    }

    public void executeTileAction(Player player, int moveDistance) {
        if (player.move(moveDistance)) {
            System.out.println(player + " passed GO and collected $200!");
            player.receiveMoney(200);
        }
        Tiles currentTile = board.get(player.getPosition());
        currentTile.executeAction(player);
    }

    private int findPosition(String destination) {
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).getName().equals(destination)) {
                return i;
            }
        }
        return -1; // Return -1 if the destination is not found
    }

    private ArrayList<String> setUpMessages(int index) {
        ArrayList<String> messageList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("tiles_subclasses/chancesMessage.csv"))) {
            String line;
            br.readLine(); // Skip the first line (header)
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length > 0) {
                    messageList.add(parts[index].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messageList;
    }

    public void setBoard(ArrayList<Tiles> board) {
        this.board = board; // Set the board for the Chances tile
    }
}
