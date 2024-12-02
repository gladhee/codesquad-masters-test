public class OutputView {

    private static final String WINNING_NUMBERS_MESSAGE = "로또 당첨 숫자: %s";
    private static final String PLAYER_NUMBERS_MESSAGE = "플레이어의 숫자: %s";
    private static final String RESULT_MESSAGE = "결과: %s";

    public void printWinningNumbers(WinningNumbers winningNumbers) {
        String formattedMessage = String.format(WINNING_NUMBERS_MESSAGE, winningNumbers);

        System.out.println(formattedMessage);
    }

    public void printPlayerNumbers(Lotto lotto) {
        String formattedMessage = String.format(PLAYER_NUMBERS_MESSAGE, lotto);

        System.out.println(formattedMessage);
    }

    public void printResult(Rank rank) {
        String message = RankMessageMapper.getMessage(rank);
        String formattedMessage = String.format(RESULT_MESSAGE, message);

        System.out.println(formattedMessage);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
