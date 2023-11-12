package christmas.domain.contants.menu;

import java.util.function.Function;

public enum BeverageMenu implements MenuInterface {
    ZERO_COLA("제로콜라", 3_000, false, (amount) -> false),
    RED_WINE("레드와인", 60_000, false, (amount) -> false),
    CHAMPAGNE("샴페인", 25_000, true, (amount) -> amount >= 120_000);

    private final String name;
    private final int price;
    private final boolean gift;
    private final Function<Integer, Boolean> giftApplicable;

    BeverageMenu(String name, int price, boolean gift, Function<Integer, Boolean> giftApplicable) {
        this.name = name;
        this.price = price;
        this.gift = gift;
        this.giftApplicable = giftApplicable;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public boolean getGift() {
        return gift;
    }

    @Override
    public boolean isGiftApplicable(int amount) {
        return giftApplicable.apply(amount);
    }

    @Override
    public String toString() {
        return name + "(" + String.format("%,d", price) + ")";
    }
}
