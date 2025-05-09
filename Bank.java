public class Bank{
    public Bank() {
    }

    public int passGo(int currentBalance) {
        int goMoney = 200; // Amount given when passing GO
        return currentBalance + goMoney;
    }
}