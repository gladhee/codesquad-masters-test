import java.util.HashMap;
import java.util.Map;

public class RankMessageMapper {

    private static final Map<Rank, String> messages = new HashMap<>();

    static {
        messages.put(Rank.SIX_MATCHES, "1등 축하드립니다!!!!");
        messages.put(Rank.FIVE_MATCHES_WITH_BONUS, "2등 축하드립니다!");
        messages.put(Rank.FIVE_MATCHES, "3등 축하드립니다");
        messages.put(Rank.FOUR_MATCHES, "4등이 되었습니다!");
        messages.put(Rank.THREE_MATCHES, "5등이 되었습니다.");
        messages.put(Rank.TWO_MATCHES, "낙첨되었습니다.");
        messages.put(Rank.ONE_MATCH, "낙첨되었습니다.");
        messages.put(Rank.NO_MATCH, "낙첨되었습니다.");
    }

    public static String getMessage(Rank rank) {
        return messages.getOrDefault(rank, "결과를 알 수 없습니다.");
    }

}