import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class NumberGenerator {

    private static final Random random = ThreadLocalRandom.current();
    private static final String UNEXPECTED_ERROR_MESSAGE = "예기치 못한 오류가 발생했습니다.";

    public static List<Integer> generateSortedUniqueNumbers() {
        Set<Integer> uniqueNumbers = getUniqueNumbers();

        List<Integer> sortedNumbers = new ArrayList<>(uniqueNumbers);
        Collections.sort(sortedNumbers);

        return sortedNumbers;
    }

    private static Set<Integer> getUniqueNumbers() {
        Set<Integer> uniqueNumbers = new HashSet<>();

        while (uniqueNumbers.size() < LottoConstants.REQUIRED_NUMBER_COUNT.getValue()) {
            int number = pickRandomNumber();
            uniqueNumbers.add(number);
        }

        return uniqueNumbers;
    }

    public static int generateBonusNumber(List<Integer> excludedNumbers) {
        return Stream.generate(NumberGenerator::pickRandomNumber)
                .filter(number -> !excludedNumbers.contains(number))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(UNEXPECTED_ERROR_MESSAGE));
    }

    private static int pickRandomNumber() {
        return random.nextInt(LottoConstants.MAX_NUMBER.getValue() - LottoConstants.MIN_NUMBER.getValue() + 1)
                + LottoConstants.MIN_NUMBER.getValue();
    }

}