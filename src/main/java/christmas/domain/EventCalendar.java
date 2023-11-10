package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class EventCalendar {
    private final LocalDate yearMonth;

    public EventCalendar(int year, int month) {
        this.yearMonth = LocalDate.of(year, month, 1);
    }

    public boolean isWeekend(int day) {
        DayOfWeek dayOfWeek = yearMonth.withDayOfMonth(day).getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
