import java.util.Scanner;

public class Human extends Player {
    private Scanner scanner;

    public Human(String name) {
        super(name);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public boolean decideToBuy(int cost) {
        System.out.println("Do you want to buy for $" + cost + "? (y/n)");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("y");
    }

}
