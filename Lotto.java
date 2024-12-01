import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private final static int REQUIRED_NUMBER_COUNT = 6;
    private final static int MIN_NUMBER = 1;
    private final static int MAX_NUMBER = 45;

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validateNumbers(numbers);
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

    private static void validateNumbers(List<Integer> numbers) {
        if (numbers.size() != REQUIRED_NUMBER_COUNT) {
            throw new IllegalArgumentException("6개의 숫자를 입력해야 합니다.");
        }

        if (hasInvalidRange(numbers)) {
            List<Integer> invalidRangeNumbers = pickInvalidRangeNumbers(numbers);
            throw new IllegalArgumentException(
                    "범위를 벗어난 숫자 " + Parser.formatNumbers(invalidRangeNumbers) + "가 포함되어 있습니다.");
        }

        if (hasDuplicates(numbers)) {
            List<Integer> duplicateNumbers = pickDuplicateNumbers(numbers);
            throw new IllegalArgumentException(
                    "같은 번호 " + Parser.formatNumbers(duplicateNumbers) + "이 이미 선택되었습니다. 다른 번호를 선택하세요.");
        }
    }

    private static boolean hasInvalidRange(List<Integer> numbers) {
        return numbers.stream().anyMatch(n -> n < MIN_NUMBER || n > MAX_NUMBER);
    }

    private static boolean hasDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        return uniqueNumbers.size() != numbers.size();
    }

    private static List<Integer> pickInvalidRangeNumbers(List<Integer> numbers) {
        List<Integer> invalidRangeNumbers = new ArrayList<>();

        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                invalidRangeNumbers.add(number);
            }
        }

        return invalidRangeNumbers;
    }

    private static List<Integer> pickDuplicateNumbers(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        List<Integer> duplicateNumbers = new ArrayList<>();

        for (int number : numbers) {
            if (!uniqueNumbers.add(number)) {
                duplicateNumbers.add(number);
            }
        }

        return duplicateNumbers;
    }

}
