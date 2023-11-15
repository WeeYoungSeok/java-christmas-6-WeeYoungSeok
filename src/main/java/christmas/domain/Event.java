package christmas.domain;

import christmas.domain.contants.Gift;
import christmas.domain.contants.event.EventDiscount;
import christmas.domain.contants.event.EventValue;
import christmas.domain.contants.menu.MenuGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Event {
    private final Map<EventDiscount, Integer> eventDiscountGroup;
    private final Map<Gift, Integer> gifts;

    public Event() {
        this.eventDiscountGroup = initEventDiscounts();
        this.gifts = initGifts();
    }

    private Map<EventDiscount, Integer> initEventDiscounts() {
        Map<EventDiscount, Integer> result = new HashMap<>();
        Arrays.stream(EventDiscount.values())
                .forEach(rank -> result.put(rank, 0));
        return result;
    }

    private Map<Gift, Integer> initGifts() {
        return new HashMap<>();
    }

    public void eventSetting(VisitDate visitDate, EventCalendar eventCalendar, StarDate starDate, Menu menu) {
        if (menu.getTotalMenuPrice() >= EventValue.ORDER_MIN_PRICE.getValue()) {
            giftSetting(visitDate, eventCalendar, menu);
            eventDiscountSetting(visitDate, eventCalendar, starDate, menu);
        }
    }

    public void eventDiscountSetting(VisitDate visitDate, EventCalendar eventCalendar, StarDate starDate, Menu menu) {
            weekdayDiscountSetting(visitDate, eventCalendar, menu);
            weekendDiscountSetting(visitDate, eventCalendar, menu);
            christmasDDayDiscountSetting(visitDate, eventCalendar);
            specialDiscountSetting(visitDate, starDate);
            eventDiscountGroupAddGift();
    }

    public void giftSetting(VisitDate visitDate, EventCalendar eventCalendar, Menu menu) {
        if (isVisitDateAllDateEvent(visitDate, eventCalendar)) {
            Arrays.stream(Gift.values())
                    .filter(gift -> gift.isGiftApplicable(menu.getTotalMenuPrice()))
                    .filter(Gift::getGift)
                    .forEach(gift -> gifts.put(gift, gift.getCount() * gift.getPrice()));
        }
    }

    public void eventDiscountGroupAddGift() {
        this.gifts.forEach((key, value) -> this.eventDiscountGroup.put(
                EventDiscount.GIFT, this.eventDiscountGroup.getOrDefault(EventDiscount.GIFT, 0) + value
        ));
    }

    public void weekdayDiscountSetting(VisitDate visitDate, EventCalendar eventCalendar, Menu menu) {
        if (!visitDate.isWeekend(eventCalendar) && isVisitDateAllDateEvent(visitDate, eventCalendar)) {
            this.eventDiscountGroup.put(EventDiscount.WEEKDAY,
                    this.eventDiscountGroup.getOrDefault(
                            EventDiscount.WEEKDAY, 0) +
                            menu.categoryMenuCount(MenuGroup.DESSERT) * EventDiscount.WEEKDAY.getDiscount()
                    );
        }
    }

    public void weekendDiscountSetting(VisitDate visitDate, EventCalendar eventCalendar, Menu menu) {
        if (visitDate.isWeekend(eventCalendar) && isVisitDateAllDateEvent(visitDate, eventCalendar)) {
            this.eventDiscountGroup.put(EventDiscount.WEEKEND, this.eventDiscountGroup.getOrDefault(
                    EventDiscount.WEEKEND, 0) +
                    menu.categoryMenuCount(MenuGroup.MAIN) * EventDiscount.WEEKEND.getDiscount());
        }
    }

    public void christmasDDayDiscountSetting(VisitDate visitDate, EventCalendar eventCalendar) {
        if (isVisitDateChristmasDDay(visitDate, eventCalendar)) {
            this.eventDiscountGroup.put(EventDiscount.CHRISTMAS,
                    this.eventDiscountGroup.getOrDefault(
                            EventDiscount.CHRISTMAS, 0) + christmasDDayCalculate(visitDate)
            );
        }
    }

    public void specialDiscountSetting(VisitDate visitDate, StarDate starDate) {
        if (visitDate.containsStarDate(starDate)) {
            this.eventDiscountGroup.put(EventDiscount.SPECIAL,
                    this.eventDiscountGroup.getOrDefault(
                            EventDiscount.SPECIAL, 0) + EventDiscount.SPECIAL.getDiscount()
            );
        }
    }

    public int christmasDDayCalculate(VisitDate visitDate) {
        return EventDiscount.CHRISTMAS.getDiscount() +
                (EventValue.CHRISTMAS_ON_THE_RISE_FORM_DAY.getValue() *
                        visitDate.christmasDDayCalculate());
    }

    public int getTotalGiftAmount() {
        return this.gifts.values()
                .stream()
                .mapToInt(amount -> amount)
                .sum();
    }

    public int getTotalEventDiscount() {
        return this.eventDiscountGroup.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != EventDiscount.GIFT)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int getTotalBenefitsAmount() {
        return this.eventDiscountGroup.values()
                .stream()
                .mapToInt(amount -> amount)
                .sum();
    }

    public boolean isVisitDateChristmasDDay(VisitDate visitDate, EventCalendar eventCalendar) {
        return visitDate.isChristmasDDay(eventCalendar);
    }

    public boolean isVisitDateAllDateEvent(VisitDate visitDate, EventCalendar eventCalendar) {
        return visitDate.containsAllDay(eventCalendar);
    }

    public Map<EventDiscount, Integer> getEventDiscountGroup() {
        return Collections.unmodifiableMap(eventDiscountGroup);
    }

    public Map<Gift, Integer> getGifts() {
        return Collections.unmodifiableMap(gifts);
    }
}
