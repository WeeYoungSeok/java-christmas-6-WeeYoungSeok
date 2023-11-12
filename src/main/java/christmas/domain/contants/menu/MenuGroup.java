package christmas.domain.contants.menu;

import java.util.Arrays;
import java.util.List;

public enum MenuGroup {
    APPETIZER("애피타이저", Arrays.asList(AppetizerMenu.values())),
    MAIN("메인", Arrays.asList(MainMenu.values())),
    DESSERT("디저트", Arrays.asList(DessertMenu.values())),
    BEVERAGE("음료", Arrays.asList(BeverageMenu.values()));


    private final String title;
    private final List<MenuInterface> menuItems;

    MenuGroup(String title, List<MenuInterface> menuGroups) {
        this.title = title;
        this.menuItems = menuGroups;
    }

    public String getTitle() {
        return title;
    }

    public List<MenuInterface> getMenuItems() {
        return menuItems;
    }
}
