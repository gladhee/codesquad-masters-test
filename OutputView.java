import java.util.List;

public class OutputView {

    private static final String WINNING_NUMBERS_MESSAGE = "로또 당첨 숫자: %s";
    private static final String PLAYER_NUMBERS_MESSAGE = "플레이어의 숫자: %s";
    private static final String MATCHED_COUNT_MESSAGE = "당첨 번호 %s개 일치!(%s)";
    private static final String BONUS_MATCHED_MESSAGE = " + 보너스 볼 일치!";
    private static final String RANK_MESSAGE = "%s (+%d)";
    private static final String ROUND_INFO_MESSAGE = "%s회차 게임(-1000)원";
    private static final String GAME_MONEY_MESSAGE = "플레이어 재산: %s원";
    private static final String GAME_END_MESSAGE = "재산이 부족해 게임이 종료되었습니다.";

    public void printWinningNumbers(WinningNumbers winningNumbers) {
        String formattedMessage = String.format(WINNING_NUMBERS_MESSAGE, winningNumbers);

        System.out.println(formattedMessage);
    }

    public void printPlayerNumbers(Lotto lotto) {
        String formattedMessage = String.format(PLAYER_NUMBERS_MESSAGE, lotto);

        System.out.println(formattedMessage);
    }

    public void printResult(LottoResult lottoResult, Rank rank) {
        List<Integer> matchingNumbers = lottoResult.getMatchingNumbers();
        String formattedMatchMessage = String.format(MATCHED_COUNT_MESSAGE, matchingNumbers.size(), Parser.formatNumbers(matchingNumbers));
        if (rank == Rank.FIVE_MATCHES_WITH_BONUS) {
            formattedMatchMessage += BONUS_MATCHED_MESSAGE;
        }
        System.out.println(formattedMatchMessage);

        String rankMessage = RankMessageMapper.getMessage(rank);
        String formattedRankMessage = String.format(RANK_MESSAGE, rankMessage, rank.getPrize());
        System.out.println(formattedRankMessage);
    }

    public void printRoundInfo(RoundManager roundManager, GameMoney gameMoney) {
        String formattedRoundMessage = String.format(ROUND_INFO_MESSAGE, roundManager);

        printGameMoney(gameMoney);
        System.out.println(formattedRoundMessage);
    }

    public void printGameMoney(GameMoney gameMoney) {
        String formattedMessage = String.format(GAME_MONEY_MESSAGE, gameMoney);

        System.out.println(formattedMessage);
    }

    public void printGameEndMessage() {
        System.out.println(GAME_END_MESSAGE);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
