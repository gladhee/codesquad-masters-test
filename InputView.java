import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public void printInputGuideMessage() {
        System.out.println("1~45 중 로또 번호를 여섯개 입력하세요.");
    }

    public String inputNumbers() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

}
