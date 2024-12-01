public class OutputView {

    private static final String WINNING_NUMBERS_MESSAGE = "로또 당첨 숫자: %s";
    private static final String PLAYER_NUMBERS_MESSAGE = "플레이어의 숫자: %s";
    private static final String MATCH_COUNT_MESSAGE = "일치한 숫자 개수: %s개";

    public void printWinningNumbers(WinningNumbers winningNumbers) {
        String formattedMessage = String.format(WINNING_NUMBERS_MESSAGE, winningNumbers);

        System.out.println(formattedMessage);
    }

    public void printPlayerNumbers(Lotto lotto) {
        String formattedMessage = String.format(PLAYER_NUMBERS_MESSAGE, lotto);

        System.out.println(formattedMessage);
    }

    public void printMatchCount(int matchCount) {
        String formattedMessage = String.format(MATCH_COUNT_MESSAGE, matchCount);

        System.out.println(formattedMessage);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
