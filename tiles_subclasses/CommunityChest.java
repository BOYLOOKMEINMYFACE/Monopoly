import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CommunityChest extends Tiles {

    private ArrayList<String> messages;
    private ArrayList<Integer> effects;

    public CommunityChest(String name) {
        super(name);
        this.messages = setUpMessages();
        this.effects = setUpEffects();
    }

    public void executeAction(Player player) {
        super.executeAction(player);
        int randomIndex = (int) (Math.random() * messages.size());
        System.out.println(messages.get(randomIndex));
        int effect = effects.get(randomIndex);
        if (effect > 0) {
            player.receiveMoney(effect);
        } else if (effect < 0) {
            player.payMoney(-effect);
        }
    }

    private ArrayList<String> setUpMessages() {
        ArrayList<String> messageList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("communityChestMessage.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length > 0) {
                    messageList.add(parts[0].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messageList;
    }

    private ArrayList<Integer> setUpEffects(){
        ArrayList<Integer> effectList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("communityChestEffects.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    effectList.add(Integer.parseInt(parts[0].trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return effectList;
    }
}
