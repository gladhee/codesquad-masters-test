import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String inputNumbers() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

}
