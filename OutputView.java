public class OutputView {

    public void printWinningNumbers(WinningNumbers winningNumbers) {
        System.out.println(MessageFormatter.formatWinningNumbers(winningNumbers));
    }

    public void printPlayerNumbers(Lotto lotto) {
        System.out.println(MessageFormatter.formatPlayerNumbers(lotto));
    }

    public void printResult(LottoResult lottoResult, Rank rank) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(MessageFormatter.formatMatchingNumbers(lottoResult, rank))
                .append(System.lineSeparator())
                .append(MessageFormatter.formatRank(rank));

        System.out.println(stringBuilder.toString());
    }

    public void printRoundInfo(RoundManager roundManager, GameMoney gameMoney) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(MessageFormatter.formatGameMoney(gameMoney))
                .append(System.lineSeparator())
                .append(MessageFormatter.formatRoundInfo(roundManager));

        System.out.println(stringBuilder.toString());
    }

    public void printGameMoney(GameMoney gameMoney) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(MessageFormatter.formatGameMoney(gameMoney))
                .append(System.lineSeparator());

        System.out.println(stringBuilder.toString());
    }

    public void printGameEndMessage() {
        System.out.println(MessageFormatter.GAME_END_MESSAGE);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
