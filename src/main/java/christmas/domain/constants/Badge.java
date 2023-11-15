package christmas.domain.constants;

import java.util.function.Predicate;

public enum Badge {
    NONE("없음", (amount) -> amount >= 0 && amount < 5_000),
    START("별", (amount) -> amount >= 5_000 && amount < 10_000),
    TREE("트리", (amount) -> amount >= 10_000 && amount < 20_000),
    SANTA("산타", (amount) -> amount >= 20_000 && amount < Integer.MAX_VALUE);

    private final String badge;
    private final Predicate<Integer> whichBadge;

    Badge(String badge, Predicate<Integer> whichBadge) {
        this.badge = badge;
        this.whichBadge = whichBadge;
    }

    public String getBadge() {
        return badge;
    }

    public boolean getWhichBadgeFromAmount(int amount) {
        return whichBadge.test(amount);
    }

    @Override
    public String toString() {
        return badge;
    }
}