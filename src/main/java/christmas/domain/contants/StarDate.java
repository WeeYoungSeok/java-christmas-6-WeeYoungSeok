package christmas.domain.contants;

import java.util.List;

public class StarDate {
    private final List<Integer> starDates;

    public StarDate(List<Integer> starDates) {
        this.starDates = starDates;
    }

    public boolean visitDateContainsStarDates(int day) {
        return false;
    }
}
