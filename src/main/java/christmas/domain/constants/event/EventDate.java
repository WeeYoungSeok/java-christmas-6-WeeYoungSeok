package christmas.domain.constants.event;

import java.util.Set;

public enum EventDate {
    NONE(0, Set.of()),
    JANUARY(1, Set.of(1)),
    FEBRUARY(2, Set.of(14)),
    MARCH(3, Set.of(1, 14)),
    APRIL(4, Set.of()),
    MAY(5, Set.of(5, 8, 15)),
    JUNE(6, Set.of(6)),
    JULY(7, Set.of()),
    AUGUST(8, Set.of(15)),
    SEPTEMBER(9, Set.of()),
    OCTOBER(10, Set.of(3, 9)),
    NOVEMBER(11, Set.of(11)),
    DECEMBER(12, Set.of(25));

    private final int month;
    private final Set<Integer> eventDates;

    EventDate(int month, Set<Integer> eventDates) {
        this.month = month;
        this.eventDates = eventDates;
    }

    public int getMonth() {
        return month;
    }

    public Set<Integer> getEventDates() {
        return eventDates;
    }
}
