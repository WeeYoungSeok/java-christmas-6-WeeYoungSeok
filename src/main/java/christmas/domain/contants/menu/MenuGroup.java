package christmas.domain.contants.menu;

import christmas.message.ErrorMessage;

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

    public static MenuInterface findMenu(String menuName) {
        return Arrays.stream(MenuGroup.values())
                .flatMap(menuGroup -> menuGroup.getMenuItems().stream()
                        .filter(menu -> menu.getName().equals(menuName)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getFormattedMessage()));
    }

    public static MenuGroup findMenuCategory(String menuName) {
        return Arrays.stream(MenuGroup.values())
                .filter(menuGroup -> menuGroup.getMenuItems().stream()
                        .anyMatch(menu -> menu.getName().equals(menuName)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getFormattedMessage()));
    }
}
