import java.util.ArrayList;

public class Chances extends Tiles{
    private ArrayList<String> chanceCards;

    public Chances() {
        super("Chance!");
    }

    public ArrayList<String> getChanceCards() {
        return chanceCards;
    }

    public void setChanceCards(ArrayList<String> chanceCards) {
        this.chanceCards = chanceCards;
    }
}