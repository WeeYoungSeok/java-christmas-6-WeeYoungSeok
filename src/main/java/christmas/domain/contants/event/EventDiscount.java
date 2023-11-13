package christmas.domain.contants.event;

public enum EventDiscount {
    WEEKDAY("평일 할인", 2023),
    WEEKEND("주말 할인", 2023),
    SPECIAL("특별 할인", 1000),
    CHRISTMAS("크리스마스 디데이 할인", 1000);

    private final String discountName;
    private final int discount;

    EventDiscount(String discountName, int discount) {
        this.discountName = discountName;
        this.discount = discount;
    }

    public String getDiscountName() {
        return discountName;
    }

    public int getDiscount() {
        return this.discount;
    }
}
