import java.util.List;

public class WinningNumbers {

    private final List<Integer> numbers;

    private WinningNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static WinningNumbers create() {
        List<Integer> numbers = NumberGenerator.generateSortedUniqueNumbers();

        return new WinningNumbers(numbers);
    }

    public int countMatchingNumbers(Lotto lotto) {
        return (int) numbers.stream()
                .filter(lotto::contains)
                .count();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return Parser.formatNumbers(numbers);
    }

}
