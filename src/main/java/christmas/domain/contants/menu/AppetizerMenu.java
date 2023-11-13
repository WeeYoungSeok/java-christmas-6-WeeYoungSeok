package christmas.domain.contants.menu;

import java.util.function.Function;

public enum AppetizerMenu implements MenuInterface {
    MUSHROOM_CREAM_SOUP("양송이수프", 6_000, false, amount -> false),
    TAPAS("타파스", 5_500, false, amount -> false),
    CAESAR_SALAD("시저샐러드", 8_000, false, amount -> false);

    private final String name;
    private final int price;

    AppetizerMenu(String name, int price) {
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
