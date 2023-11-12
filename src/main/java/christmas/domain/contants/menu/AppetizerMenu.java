package christmas.domain.contants.menu;

import java.util.function.Function;

public enum AppetizerMenu implements MenuInterface {
    MUSHROOM_CREAM_SOUP("양송이수프", 6_000, false, amount -> false),
    TAPAS("타파스", 5_500, false, amount -> false),
    CAESAR_SALAD("시저샐러드", 8_000, false, amount -> false);

    private final String name;
    private final int price;
    private final boolean gift;
    private final Function<Integer, Boolean> giftApplicable;

    AppetizerMenu(String name, int price, boolean gift, Function<Integer, Boolean> giftApplicable) {
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
