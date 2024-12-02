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
        inputView.printInputGuideMessage();

        Lotto playerLotto = getLottoFromInput();
        inputView.close();
        outputView.printPlayerNumbers(playerLotto);

        WinningNumbers winningNumbers = WinningNumbers.create();
        outputView.printWinningNumbers(winningNumbers);

        Rank rank = winningNumbers.determineRank(playerLotto);
        outputView.printResult(rank);
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
