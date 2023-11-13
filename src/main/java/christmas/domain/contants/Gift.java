package christmas.domain.contants;

import java.util.function.Function;

public enum Gift {
    CHAMPAGNE("샴페인", 25_000, true, (amount) -> amount >= 120_000);

    private final String name;
    private final int price;
    private final boolean gift;
    private final Function<Integer, Boolean> giftApplicable;

    Gift(String name, int price, boolean gift, Function<Integer, Boolean> giftApplicable) {
        this.name = name;
        this.price = price;
        this.gift = gift;
        this.giftApplicable = giftApplicable;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public boolean getGift() {
        return gift;
    }

    public boolean isGiftApplicable(int amount) {
        return giftApplicable.apply(amount);
    }
}
