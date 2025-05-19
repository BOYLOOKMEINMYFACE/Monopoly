
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Chances extends Tiles {

    private ArrayList<String> messages;
    private ArrayList<String> destinations;

    public Chances(String name) {
        super(name);
        this.messages = setUpMessages(0);
        this.destinations = setUpMessages(1);
    }

    public void executeAction(Player player) {
        super.executeAction(player);
        int randomIndex = (int) (Math.random() * messages.size());
        System.out.println(messages.get(randomIndex));
        String destination = destinations.get(randomIndex);
        if (!(destination.equals("null")|| destination.equals("Go"))) {
            player.move(destination);
        }
    }

    private ArrayList<String> setUpMessages(int index) {
        ArrayList<String> messageList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("communityChestMessage.csv"))) {
            String line;
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
}




