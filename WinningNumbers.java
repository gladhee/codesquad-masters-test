import java.util.List;

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

    public int countMatchingNumbers(Lotto lotto) {
        return (int) winningNumbers.stream()
                .filter(lotto::contains)
                .count();
    }

    public boolean isBonusNumberMatched(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }

    @Override
    public String toString() {
        return Parser.formatNumbers(winningNumbers);
    }

}
