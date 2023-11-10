package christmas.domain;

import java.util.List;

public class StarDate {
    private final List<Integer> starDates;

    public StarDate(List<Integer> starDates) {
        this.starDates = starDates;
    }

    public boolean visitDateContainsStarDates(int day) {
        return starDates.contains(day);
    }
}
