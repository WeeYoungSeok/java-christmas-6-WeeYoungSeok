package christmas.domain.constants.event;

public enum EventValue {
    ORDER_MIN_PRICE(10000),

    CHRISTMAS_START_DAY(1),
    CHRISTMAS_END_DAY(25),
    CHRISTMAS_ON_THE_RISE_FORM_DAY(100),

    DECEMBER_START_DAY(1),
    DECEMBER_END_DAY(31);

    private final int value;

    EventValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
