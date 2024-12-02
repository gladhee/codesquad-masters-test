import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoValidator {

    public static void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicates(numbers);
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.REQUIRED_NUMBER_COUNT.getValue()) {
            throw new IllegalArgumentException("6개의 숫자를 입력해야 합니다.");
        }
    }

    private static void validateRange(List<Integer> numbers) {
        List<Integer> invalidRangeNumbers = numbers.stream()
                .filter(n -> n < LottoConstants.MIN_NUMBER.getValue() || n > LottoConstants.MAX_NUMBER.getValue())
                .toList();

        if (!invalidRangeNumbers.isEmpty()) {
            throw new IllegalArgumentException(
                    MessageFormatter.formatInvalidRangeMessage(invalidRangeNumbers));
        }
    }

    private static void validateDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        if (uniqueNumbers.size() != numbers.size()) {
            List<Integer> duplicateNumbers = findDuplicates(numbers);
            throw new IllegalArgumentException(
                    MessageFormatter.formatDuplicateNumberMessage(duplicateNumbers));
        }
    }

    private static List<Integer> findDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        List<Integer> duplicates = new ArrayList<>();

        for (int number : numbers) {
            if (!uniqueNumbers.add(number)) {
                duplicates.add(number);
            }
        }

        return duplicates;
    }

}