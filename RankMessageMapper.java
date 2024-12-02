import java.util.HashMap;
import java.util.Map;

public class RankMessageMapper {

    private static final Map<Rank, String> messages = new HashMap<>();

    static {
        messages.put(Rank.SIX_MATCHES, "6개 일치 1등 축하드립니다!");
        messages.put(Rank.FIVE_MATCHES_WITH_BONUS, "5개 일치, 보너스 볼 일치. 2등 축하드립니다!");
        messages.put(Rank.FIVE_MATCHES, "5개 일치. 3등 축하드립니다!");
        messages.put(Rank.FOUR_MATCHES, "4개 일치. 4등 축하드립니다!");
        messages.put(Rank.THREE_MATCHES, "3개 일치. 5등 축하드립니다!");
        messages.put(Rank.TWO_MATCHES, "2개 일치. 낙첨입니다.");
        messages.put(Rank.ONE_MATCH, "1개 일치. 낙첨입니다.");
        messages.put(Rank.NO_MATCH, "0개 일치. 낙첨입니다.");
    }

    public static String getMessage(Rank rank) {
        return messages.getOrDefault(rank, "결과를 알 수 없습니다.");
    }

}