import java.util.List;

public class LottoResult {

    private final List<Integer> matchingNumbers;
    private final boolean bonusMatch;

    private LottoResult(List<Integer> matchingNumbers, boolean bonusMatch) {
        this.matchingNumbers = matchingNumbers;
        this.bonusMatch = bonusMatch;
    }

    public static LottoResult from(List<Integer> matchingNumbers, boolean bonusMatch) {
        return new LottoResult(matchingNumbers, bonusMatch);
    }

    public Rank determineRank() {
        int countOfMatch = matchingNumbers.size();

        return Rank.valueOf(countOfMatch, bonusMatch);
    }

    public List<Integer> getMatchingNumbers() {
        return matchingNumbers;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }

}
