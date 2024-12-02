import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    private static final String DELIMITER = ",";
    private static final String INVALID_NUMBER_MESSAGE = "숫자가 아닌 값이 포함되어 있습니다. 다시 번호를 선택하세요.";
    private static final String JOIN_DELIMITER = ", ";

    public static List<Integer> stringToNumbers(String value) {
        String[] values = value.split(DELIMITER);

        return Arrays.stream(values)
                .map(String::trim)
                .map(Parser::stringToInt)
                .toList();
    }

    private static int stringToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_MESSAGE);
        }
    }

    public static String formatNumbers(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    public static String formatNumbersWithBonusNumber(List<Integer> numbers, int bonusNumber) {
        return formatNumbers(numbers) + " + 보너스 숫자 " + bonusNumber;
    }

}
