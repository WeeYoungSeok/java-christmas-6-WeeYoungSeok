package christmas.domain.contants.menu;

public interface MenuInterface {
    String getName();
    int getPrice();
    boolean getGift();
    boolean isGiftApplicable(int amount);
}
