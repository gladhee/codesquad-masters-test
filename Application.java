public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        GameMoney gameMoney = new GameMoney();
        RoundManager roundManager = new RoundManager();
        LottoGame lottoGame = new LottoGame(inputView, outputView, gameMoney, roundManager);

        lottoGame.run();
    }

}
