package christmas.domain.constants;

import java.util.function.Function;

public enum Gift {
    CHAMPAGNE("샴페인", 25_000, 1, true, (amount) -> amount >= 120_000);

    private final String name;
    private final int price;
    private final int count;
    private final boolean gift;
    private final Function<Integer, Boolean> giftApplicable;

    Gift(String name, int price, int count, boolean gift, Function<Integer, Boolean> giftApplicable) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.gift = gift;
        this.giftApplicable = giftApplicable;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getCount() {
        return count;
    }

    public boolean getGift() {
        return gift;
    }

    public boolean isGiftApplicable(int amount) {
        return giftApplicable.apply(amount);
    }
}
