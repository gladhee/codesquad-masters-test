import java.util.List;

public class LottoGame {

    private final InputView inputView;
    private final OutputView outputView;
    private final GameMoney gameMoney;
    private final RoundManager roundManager;

    public LottoGame(InputView inputView, OutputView outputView, GameMoney gameMoney, RoundManager roundManager) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameMoney = gameMoney;
        this.roundManager = roundManager;
    }

    public void run() {
        while (gameMoney.canPlay()) {
            playRound();
        }
        outputView.printGameEndMessage();
    }

    private void playRound() {
        outputView.printRoundInfo(roundManager, gameMoney);
        gameMoney.deductGameCost();

        Lotto playerLotto = getPlayerLotto();
        outputView.printPlayerNumbers(playerLotto);

        LottoResult lottoResult = calculateResult(playerLotto);
        processResult(lottoResult);

        roundManager.increaseRound();
    }

    private Lotto getPlayerLotto() {
        inputView.printInputGuideMessage();
        while (true) {
            try {
                String inputNumbers = inputView.inputNumbers();

                return Lotto.from(inputNumbers);
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

    private LottoResult calculateResult(Lotto playerLotto) {
        WinningNumbers winningNumbers = WinningNumbers.create();
        outputView.printWinningNumbers(winningNumbers);

        List<Integer> matchingNumbers = winningNumbers.findMatchingNumbers(playerLotto);
        boolean bonusMatch = winningNumbers.isBonusNumberMatched(playerLotto);

        return LottoResult.from(matchingNumbers, bonusMatch);
    }

    private void processResult(LottoResult lottoResult) {
        Rank rank = lottoResult.determineRank();
        gameMoney.addPrize(rank.getPrize());
        outputView.printResult(lottoResult, rank);
        outputView.printGameMoney(gameMoney);
    }

}
