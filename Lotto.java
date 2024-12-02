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

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return Parser.formatNumbers(numbers);
    }

}
