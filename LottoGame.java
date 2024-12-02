public class LottoGame {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
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
