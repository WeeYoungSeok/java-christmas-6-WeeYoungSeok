package christmas.domain.contants.menu;

import java.util.function.Function;

public enum MainMenu implements MenuInterface {
    T_BONE_STEAK("티본스테이크", 55_000, false, (amount) -> false),
    BBQ_RIBS("바비큐립", 54_000, false, (amount) -> false),
    SEAFOOD_PASTA("해산물파스타", 35_000, false, (amount) -> false),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, false, (amount) -> false);

    private final String name;
    private final int price;
    private final boolean gift;
    private final Function<Integer, Boolean> giftApplicable;

    MainMenu(String name, int price, boolean gift, Function<Integer, Boolean> giftApplicable) {
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
