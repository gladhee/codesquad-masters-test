import java.util.Arrays;

public enum Rank {

    SIX_MATCHES(6, false, 1_000_000),
    FIVE_MATCHES_WITH_BONUS(5, true, 100_000),
    FIVE_MATCHES(5, false, 10_000),
    FOUR_MATCHES(4, false, 5_000),
    THREE_MATCHES(3, false, 1_000),
    TWO_MATCHES(2, false, 0),
    ONE_MATCH(1, false, 0),
    NO_MATCH(0, false, 0);

    private final int requiredMatchCount;
    private final boolean requiresBonusMatch;
    private final int prize;

    Rank(int requiredMatchCount, boolean requiresBonusMatch, int prize) {
        this.requiredMatchCount = requiredMatchCount;
        this.requiresBonusMatch = requiresBonusMatch;
        this.prize = prize;
    }

    public static Rank valueOf(int matchCount, boolean bonusMatch) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isMatch(matchCount, bonusMatch))
                .findFirst()
                .orElse(NO_MATCH);
    }

    private boolean isMatch(int matchCount, boolean bonusMatch) {
        if (this.requiresBonusMatch) {
            return matchCount == this.requiredMatchCount && bonusMatch;
        }
        return matchCount == this.requiredMatchCount;
    }

    public int getPrize() {
        return prize;
    }

}
