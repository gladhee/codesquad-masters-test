import java.util.Arrays;

public enum Rank {

    SIX_MATCHES(6, false),
    FIVE_MATCHES_WITH_BONUS(5, true),
    FIVE_MATCHES(5, false),
    FOUR_MATCHES(4, false),
    THREE_MATCHES(3, false),
    TWO_MATCHES(2, false),
    ONE_MATCH(1, false),
    NO_MATCH(0, false);

    private final int requiredMatchCount;
    private final boolean requiresBonusMatch;

    Rank(int requiredMatchCount, boolean requiresBonusMatch) {
        this.requiredMatchCount = requiredMatchCount;
        this.requiresBonusMatch = requiresBonusMatch;
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

}
