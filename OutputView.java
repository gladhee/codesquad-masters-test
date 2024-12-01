public class OutputView {

    private static final String WINNING_NUMBERS_MESSAGE = "로또 당첨 숫자: ";
    private static final String PLAYER_NUMBERS_MESSAGE = "플레이어의 숫자: ";
    private static final String MATCH_COUNT_MESSAGE = "일치한 숫자 개수: ";

    public void printWinningNumbers(WinningNumbers winningNumbers) {
        System.out.println(WINNING_NUMBERS_MESSAGE + winningNumbers);
    }

    public void printPlayerNumbers(Lotto lotto) {
        System.out.println(PLAYER_NUMBERS_MESSAGE + lotto);
    }

    public void printMatchCount(int matchCount) {
        System.out.println(MATCH_COUNT_MESSAGE + matchCount + "개");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
