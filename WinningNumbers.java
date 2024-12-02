import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    private WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumbers create() {
        List<Integer> numbers = NumberGenerator.generateSortedUniqueNumbers();
        int bonusNumber = NumberGenerator.generateBonusNumber(numbers);

        return new WinningNumbers(numbers, bonusNumber);
    }

    public List<Integer> findMatchingNumbers(Lotto lotto) {
        return winningNumbers.stream()
                .filter(lotto::contains)
                .collect(Collectors.toList());
    }

    public boolean isBonusNumberMatched(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }

    @Override
    public String toString() {
        return Parser.formatNumbersWithBonusNumber(winningNumbers, bonusNumber);
    }

}
