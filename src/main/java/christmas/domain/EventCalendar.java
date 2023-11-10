package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class EventCalendar {
    private final LocalDate currentDate;

    public EventCalendar(int year, int month) {
        this.currentDate = LocalDate.of(year, month, 1);
    }

    public boolean isWeekend(int day) {
        DayOfWeek dayOfWeek = currentDate.withDayOfMonth(day).getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public int getStartDate() {
        return currentDate.getDayOfMonth();
    }

    public int getEndDate() {
        return currentDate.lengthOfMonth();
    }
}
