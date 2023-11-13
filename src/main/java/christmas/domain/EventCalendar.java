package christmas.domain;

import christmas.domain.contants.event.EventValue;

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

    public boolean isChristmasDDay(int day) {
        return day >= EventValue.CHRISTMAS_START_DAY.getValue() && day <= EventValue.CHRISTMAS_END_DAY.getValue();
    }

    public int getYear() {
        return currentDate.getYear();
    }

    public int getMonth() {
        return currentDate.getMonthValue();
    }

    public int getStartDate() {
        return currentDate.getDayOfMonth();
    }

    public int getEndDate() {
        return currentDate.lengthOfMonth();
    }
}
