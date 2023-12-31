package christmas.domain;

import christmas.domain.constants.event.EventValue;
import christmas.message.ErrorMessage;

public class VisitDate {
    private final int visitDay;

    private VisitDate(int visitDay) {
        this.visitDay = visitDay;
    }

    public static VisitDate of(final int visitDay, EventCalendar eventCalendar) {
        validate(visitDay, eventCalendar.getStartDate(), eventCalendar.getEndDate());
        return new VisitDate(visitDay);
    }

    public static void validate(final int visitDay, final int firstDay, final int endDay) {
        validateDayInMonth(visitDay, firstDay, endDay);
    }

    public static void validateDayInMonth(final int visitDay, final int firstDay, final int endDay) {
        if (visitDay < firstDay || visitDay > endDay) {
            throw new IllegalArgumentException(
                    String.format(ErrorMessage.OUT_OF_RANGE_DATE.getReasonFormattedMessage(), firstDay, endDay)
            );
        }
    }

    public boolean isWeekend(EventCalendar eventCalendar) {
        return eventCalendar.isWeekend(this.visitDay);
    }

    public boolean isChristmasDDay(EventCalendar eventCalendar) {
        return eventCalendar.isChristmasDDay(this.visitDay);
    }

    public boolean containsAllDay(EventCalendar eventCalendar) {
        return eventCalendar.containsAllDay(this.visitDay);
    }

    public int christmasDDayCalculate() {
        return this.visitDay - EventValue.CHRISTMAS_START_DAY.getValue();
    }

    public boolean containsStarDate(StarDate starDate) {
        return starDate.visitDateContainsStarDates(this.visitDay);
    }
}
