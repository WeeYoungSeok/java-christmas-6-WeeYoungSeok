package christmas.domain;

import christmas.domain.contants.EventDiscount;

import java.util.Map;

public class Event {
    private final Map<EventDiscount, Integer> eventDiscountGroup;

    public Event(VisitDate visitDate, EventCalendar eventCalendar, Menu menu) {
        this.eventDiscountGroup = eventDiscountSetting();
    }

    public Map<EventDiscount, Integer> eventDiscountSetting() {
        return null;
    }

    public Map<EventDiscount, Integer> weekdayDiscountSetting(Map<EventDiscount, Integer> eventDiscounts) {
        return null;
    }

    public int getWeekdayDiscount() {
        return 0;
    }
}
