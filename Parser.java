import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    private static final String DELIMITER = ",";

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
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }

    public static String formatNumbers(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

}
