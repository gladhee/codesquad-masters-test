import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        LottoValidator.validate(numbers);
        this.numbers = numbers;
    }

    public static Lotto from(String inputNumbers) {
        List<Integer> numbers = Parser.stringToNumbers(inputNumbers);

        return new Lotto(numbers);
    }

    public int countMatchingNumbers(WinningNumbers winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    @Override
    public String toString() {
        return Parser.formatNumbers(numbers);
    }

}
