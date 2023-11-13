package christmas.domain.contants;

public enum EventDiscount {
    WEEKDAY(2023),
    WEEKEND(2023),
    SPECIAL(1000),
    CHRISTMAS(1000);

    private final int discount;

    EventDiscount(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return this.discount;
    }
}
