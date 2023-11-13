package christmas.domain;

import christmas.domain.contants.event.EventDate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StarDate {
    private final Set<Integer> starDates;

    public StarDate(EventCalendar eventCalendar) {
        this.starDates = new HashSet<>() {{
            addAll(starDatesSettingWithSunday(eventCalendar));
            addAll(starDateSettingWithSpecialDay(eventCalendar));
        }};
    }

    public boolean visitDateContainsStarDates(int day) {
        return starDates.contains(day);
    }

    public Set<Integer> starDatesSettingWithSunday(EventCalendar eventCalendar) {
        LocalDate localDate = LocalDate.of(eventCalendar.getYear(), eventCalendar.getMonth(), eventCalendar.getStartDate());
        Set<Integer> sundays = new HashSet<>();
        LocalDate currentSunday = localDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));

        while (!currentSunday.isAfter(localDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY)))) {
            sundays.add(currentSunday.getDayOfMonth());
            currentSunday = currentSunday.plusWeeks(1);
        }
        return sundays;
    }

    public Set<Integer> starDateSettingWithSpecialDay(EventCalendar eventCalendar) {
        return Arrays.stream(EventDate.values())
                .filter(eventDate -> eventDate.getMonth() == eventCalendar.getMonth())
                .findFirst()
                .map(EventDate::getEventDates)
                .orElse(EventDate.NONE.getEventDates());
    }
}
