package christmas.domain;

import java.time.LocalDate;

public class EventCalendar {
    private final LocalDate yearMonth;

    public EventCalendar(int year, int month) {
        this.yearMonth = LocalDate.of(year, month, 1);
    }

    public boolean isWeekend(int day) {
       return false;
    }
}
