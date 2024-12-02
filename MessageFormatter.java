import java.util.List;

public class MessageFormatter {

    private static final String WINNING_NUMBERS_MESSAGE = "로또 당첨 숫자: %s";
    private static final String PLAYER_NUMBERS_MESSAGE = "플레이어의 숫자: %s";
    private static final String MATCHED_COUNT_MESSAGE = "당첨 번호 %s개 일치!(%s)";
    private static final String BONUS_MATCHED_MESSAGE = " + 보너스 볼 일치!";
    private static final String RANK_MESSAGE = "%s (+%d)";
    private static final String ROUND_INFO_MESSAGE = "%s회차 게임(-1000)원";
    private static final String GAME_MONEY_MESSAGE = "플레이어 재산: %s원";
    public static final String GAME_END_MESSAGE = "재산이 부족해 게임이 종료되었습니다.";

    public static String formatWinningNumbers(WinningNumbers winningNumbers) {
        return String.format(WINNING_NUMBERS_MESSAGE, winningNumbers);
    }

    public static String formatPlayerNumbers(Lotto lotto) {
        return String.format(PLAYER_NUMBERS_MESSAGE, lotto);
    }

    public static String formatMatchingNumbers(LottoResult lottoResult, Rank rank) {
        StringBuilder formattedMessage = new StringBuilder();
        List<Integer> matchingNumbers = lottoResult.getMatchingNumbers();

        formattedMessage.append(String.format(MATCHED_COUNT_MESSAGE, matchingNumbers.size(), rank.getPrize()));

        if (lottoResult.isBonusMatch()) {
            formattedMessage.append(BONUS_MATCHED_MESSAGE);
        }

        return formattedMessage.toString();
    }

    public static String formatRank(Rank rank) {
        String rankMessage = RankMessageMapper.getMessage(rank);
        return String.format(RANK_MESSAGE, rankMessage, rank.getPrize());
    }

    public static String formatRoundInfo(RoundManager roundManager) {
        return String.format(ROUND_INFO_MESSAGE, roundManager);
    }

    public static String formatGameMoney(GameMoney gameMoney) {
        return String.format(GAME_MONEY_MESSAGE, gameMoney);
    }

}
