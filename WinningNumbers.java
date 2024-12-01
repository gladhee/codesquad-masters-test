import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {

    private final List<Integer> numbers;

    private WinningNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static WinningNumbers create() {
        List<Integer> numbers = NumberGenerator.generateSortedUniqueNumbers();

        return new WinningNumbers(numbers);
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

}
