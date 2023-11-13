package christmas.domain.contants.menu;

public enum DessertMenu implements MenuInterface {
    CHOCO_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000);

    private final String name;
    private final int price;

    DessertMenu(String name, int price) {
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
