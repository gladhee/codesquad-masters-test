public class RoundManager {

    private int currentRound;

    public RoundManager() {
        this.currentRound = 1;
    }

    public void increaseRound() {
        currentRound++;
    }

    @Override
    public String toString() {
        return String.valueOf(currentRound);
    }

}
