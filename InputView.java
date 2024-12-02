import java.util.Scanner;

public class InputView {

    private final Scanner scanner;
    private static final String INPUT_GUIDE_MESSAGE = "플레이어는 1~45 사이 로또 번호 여섯개를 선택하세요 (쉼표로 구분)";

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public void printInputGuideMessage() {
        System.out.println(INPUT_GUIDE_MESSAGE);
    }

    public String inputNumbers() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

}
