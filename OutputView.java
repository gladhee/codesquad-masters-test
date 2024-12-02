public class OutputView {

    public void printWinningNumbers(WinningNumbers winningNumbers) {
        System.out.println(MessageFormatter.formatWinningNumbers(winningNumbers));
    }

    public void printPlayerNumbers(Lotto lotto) {
        System.out.println(MessageFormatter.formatPlayerNumbers(lotto));
    }

    public void printResult(LottoResult lottoResult, Rank rank) {
        System.out.println(MessageFormatter.formatMatchingNumbers(lottoResult, rank));
        System.out.println(MessageFormatter.formatRank(rank));
    }

    public void printRoundInfo(RoundManager roundManager, GameMoney gameMoney) {
        System.out.println(MessageFormatter.formatRoundInfo(roundManager));
        printGameMoney(gameMoney);
    }

    public void printGameMoney(GameMoney gameMoney) {
        System.out.println(MessageFormatter.formatGameMoney(gameMoney));
    }

    public void printGameEndMessage() {
        System.out.println(MessageFormatter.GAME_END_MESSAGE);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
