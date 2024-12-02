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
            gameMoney.deductGameCost();
            playRound();
            roundManager.increaseRound();
        }
    }

    private void playRound() {
        inputView.printInputGuideMessage();
        Lotto playerLotto = getLottoFromInput();

        LottoResult lottoResult = calculateLottoResult(playerLotto);
        Rank rank = lottoResult.determineRank();

        gameMoney.addPrize(rank.getPrize());
    }

    private LottoResult calculateLottoResult(Lotto playerLotto) {
        WinningNumbers winningNumbers = WinningNumbers.create();
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
