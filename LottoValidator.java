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
                    "범위를 벗어난 숫자 " + Parser.formatNumbers(invalidRangeNumbers) + "가 포함되어 있습니다.");
        }
    }

    private static void validateDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        if (uniqueNumbers.size() != numbers.size()) {
            List<Integer> duplicateNumbers = findDuplicates(numbers);
            throw new IllegalArgumentException(
                    "같은 번호 " + Parser.formatNumbers(duplicateNumbers) + "이 이미 선택되었습니다. 다른 번호를 선택하세요.");
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