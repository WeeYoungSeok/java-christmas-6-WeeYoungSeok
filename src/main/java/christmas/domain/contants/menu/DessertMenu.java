package christmas.domain.contants.menu;

import java.util.function.Function;

public enum DessertMenu implements MenuInterface {
    CHOCO_CAKE("초코케이크", 15_000, false, (amount) -> false),
    ICE_CREAM("아이스크림", 5_000, false, (amount) -> false);

    private final String name;
    private final int price;
    private final boolean gift;
    private final Function<Integer, Boolean> giftApplicable;

    DessertMenu(String name, int price, boolean gift, Function<Integer, Boolean> giftApplicable) {
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
