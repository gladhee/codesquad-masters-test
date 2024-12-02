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
            outputView.printRoundInfo(roundManager, gameMoney);
            gameMoney.deductGameCost();
            playRound();
            roundManager.increaseRound();
        }
        outputView.printGameEndMessage();
    }

    private void playRound() {
        inputView.printInputGuideMessage();
        Lotto playerLotto = getLottoFromInput();
        outputView.printPlayerNumbers(playerLotto);

        LottoResult lottoResult = getResult(playerLotto);
        Rank rank = lottoResult.determineRank();

        gameMoney.addPrize(rank.getPrize());

        outputView.printResult(lottoResult, rank);
        outputView.printGameMoney(gameMoney);
    }

    private LottoResult getResult(Lotto playerLotto) {
        WinningNumbers winningNumbers = WinningNumbers.create();
        outputView.printWinningNumbers(winningNumbers);
        List<Integer> matchingNumbers = winningNumbers.findMatchingNumbers(playerLotto);
        boolean bonusMatch = winningNumbers.isBonusNumberMatched(playerLotto);

        return LottoResult.from(matchingNumbers, bonusMatch);
    }

    private Lotto getLottoFromInput() {
        while (true) {
            try {
                String input = inputView.inputNumbers();

                return Lotto.from(input);
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

}
