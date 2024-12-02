public class GameMoney {

    private static final int GAME_COST = 1_000;
    private static final int INITIAL_BALANCE = 10_000;
    private int balance;

    public GameMoney() {
        this.balance = INITIAL_BALANCE;
    }

    public boolean canPlay() {
        return balance >= GAME_COST;
    }

    public void deductGameCost() {
        balance -= GAME_COST;
    }

    public void addPrize(int prizeMoney) {
        balance += prizeMoney;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.valueOf(balance);
    }

}
