package christmas.domain;

import christmas.domain.contants.event.EventDiscount;
import christmas.domain.contants.event.EventValue;
import christmas.domain.contants.menu.MenuGroup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Event {
    private final Map<EventDiscount, Integer> eventDiscountGroup;

    public Event(VisitDate visitDate, EventCalendar eventCalendar, Menu menu, StarDate starDate) {
        this.eventDiscountGroup = eventDiscountSetting(visitDate, eventCalendar, starDate, menu);
    }

    public Map<EventDiscount, Integer> eventDiscountSetting(VisitDate visitDate, EventCalendar eventCalendar, StarDate starDate, Menu menu) {
        Map<EventDiscount, Integer> eventDiscounts = new HashMap<>();
        if (menu.getTotalMenuPrice() >= EventValue.ORDER_MIN_PRICE.getValue()) {
            weekdayDiscountSetting(visitDate, eventCalendar, menu, eventDiscounts);
            weekendDiscountSetting(visitDate, eventCalendar, menu, eventDiscounts);
            christmasDDayDiscountSetting(visitDate, eventCalendar, eventDiscounts);
            specialDiscountSetting(visitDate, starDate, eventDiscounts);
            return eventDiscounts;
        }
        Arrays.stream(EventDiscount.values())
                .forEach(eventDiscount -> eventDiscounts.put(eventDiscount, 0));
        return eventDiscounts;
    }

    public void weekdayDiscountSetting(VisitDate visitDate, EventCalendar eventCalendar, Menu menu, Map<EventDiscount, Integer> eventDiscounts) {
        if (!visitDate.isWeekend(eventCalendar) && isVisitDateAllDateEvent(visitDate, eventCalendar)) {
            eventDiscounts.put(EventDiscount.WEEKDAY,
                    menu.categoryMenuCount(MenuGroup.DESSERT) * EventDiscount.WEEKDAY.getDiscount());
        }
    }

    public void weekendDiscountSetting(VisitDate visitDate, EventCalendar eventCalendar, Menu menu, Map<EventDiscount, Integer> eventDiscounts) {
        if (visitDate.isWeekend(eventCalendar) && isVisitDateAllDateEvent(visitDate, eventCalendar)) {
            eventDiscounts.put(EventDiscount.WEEKEND,
                    menu.categoryMenuCount(MenuGroup.MAIN) * EventDiscount.WEEKEND.getDiscount());
        }
    }

    public void christmasDDayDiscountSetting(VisitDate visitDate, EventCalendar eventCalendar, Map<EventDiscount, Integer> eventDiscounts) {
        if (isVisitDateChristmasDDay(visitDate, eventCalendar)) {
            eventDiscounts.put(EventDiscount.CHRISTMAS, christmasDDayCalculate(visitDate));
        }
    }

    public void specialDiscountSetting(VisitDate visitDate, StarDate starDate, Map<EventDiscount, Integer> eventDiscounts) {
        if (visitDate.containsStarDate(starDate)) {
            eventDiscounts.put(EventDiscount.SPECIAL, EventDiscount.SPECIAL.getDiscount());
        }
    }

    public int christmasDDayCalculate(VisitDate visitDate) {
        return EventDiscount.CHRISTMAS.getDiscount() +
                (EventValue.CHRISTMAS_ON_THE_RISE_FORM_DAY.getValue() *
                        visitDate.christmasDDayCalculate());
    }


    public int getWeekdayDiscount() {
        return this.eventDiscountGroup.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(EventDiscount.WEEKDAY))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int getWeekendDiscount() {
        return this.eventDiscountGroup.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(EventDiscount.WEEKEND))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int getSpecialDiscount() {
        return this.eventDiscountGroup.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(EventDiscount.SPECIAL))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public boolean isVisitDateChristmasDDay(VisitDate visitDate, EventCalendar eventCalendar) {
        return visitDate.isChristmasDDay(eventCalendar);
    }

    public boolean isVisitDateAllDateEvent(VisitDate visitDate, EventCalendar eventCalendar) {
        return visitDate.containsAllDay(eventCalendar);
    }
}
