package christmas.domain.contants.menu;

import java.util.function.Function;

public enum BeverageMenu implements MenuInterface {
    ZERO_COLA("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000);

    private final String name;
    private final int price;
    private final boolean gift;
    private final Function<Integer, Boolean> giftApplicable;

    BeverageMenu(String name, int price) {
        this.name = name;
        this.price = price;
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
    public String toString() {
        return name + "(" + String.format("%,d", price) + ")";
    }
}
