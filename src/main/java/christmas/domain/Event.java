package christmas.domain;

import christmas.domain.contants.event.EventDiscount;
import christmas.domain.contants.menu.MenuGroup;

import java.util.HashMap;
import java.util.Map;

public class Event {
    private final Map<EventDiscount, Integer> eventDiscountGroup;

    public Event(VisitDate visitDate, EventCalendar eventCalendar, Menu menu) {
        this.eventDiscountGroup = eventDiscountSetting(visitDate, eventCalendar, menu);
    }

    public Map<EventDiscount, Integer> eventDiscountSetting(VisitDate visitDate, EventCalendar eventCalendar, Menu menu) {
        Map<EventDiscount, Integer> eventDiscounts = new HashMap<>();
        weekdayDiscountSetting(visitDate, eventCalendar, menu, eventDiscounts);
        weekendDiscountSetting(visitDate, eventCalendar, menu, eventDiscounts);
        return eventDiscounts;
    }

    public void weekdayDiscountSetting(VisitDate visitDate, EventCalendar eventCalendar, Menu menu, Map<EventDiscount, Integer> eventDiscounts) {
        if (!visitDate.isWeekend(eventCalendar)) {
            eventDiscounts.put(EventDiscount.WEEKDAY, menu.categoryMenuCount(MenuGroup.DESSERT));
        }
    }

    public void weekendDiscountSetting(VisitDate visitDate, EventCalendar eventCalendar, Menu menu, Map<EventDiscount, Integer> eventDiscounts) {
        if (visitDate.isWeekend(eventCalendar)) {
            eventDiscounts.put(EventDiscount.WEEKEND, menu.categoryMenuCount(MenuGroup.MAIN));
        }
    }


    public int getWeekdayDiscount() {
        return this.eventDiscountGroup.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(EventDiscount.WEEKDAY))
                .mapToInt(entry -> entry.getValue() * entry.getKey().getDiscount())
                .sum();
    }

    public int getWeekendDiscount() {
        return this.eventDiscountGroup.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(EventDiscount.WEEKEND))
                .mapToInt(entry -> entry.getValue() * entry.getKey().getDiscount())
                .sum();
    }
}
