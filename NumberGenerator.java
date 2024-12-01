import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class NumberGenerator {

    public static List<Integer> generateSortedUniqueNumbers() {
        Set<Integer> uniqueNumbers = getUniqueNumbers();

        List<Integer> sortedNumbers = new ArrayList<>(uniqueNumbers);
        Collections.sort(sortedNumbers);

        return sortedNumbers;
    }

    private static Set<Integer> getUniqueNumbers() {
        Set<Integer> uniqueNumbers = new HashSet<>();
        Random random = ThreadLocalRandom.current();

        while (uniqueNumbers.size() < LottoConstants.REQUIRED_NUMBER_COUNT.getValue()) {
            int number = random.nextInt(LottoConstants.MAX_NUMBER.getValue() - LottoConstants.MIN_NUMBER.getValue() + 1)
                    + LottoConstants.MIN_NUMBER.getValue();
            uniqueNumbers.add(number);
        }

        return uniqueNumbers;
    }

}