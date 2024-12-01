import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class NumberGenerator {

    private static final int NUMBER_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public static List<Integer> generateSortedUniqueNumbers() {
        Set<Integer> uniqueNumbers = new HashSet<>();
        Random random = ThreadLocalRandom.current();

        while (uniqueNumbers.size() < NUMBER_COUNT) {
            int number = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
            uniqueNumbers.add(number);
        }

        List<Integer> sortedNumbers = new ArrayList<>(uniqueNumbers);
        Collections.sort(sortedNumbers);

        return sortedNumbers;
    }

}