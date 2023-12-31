package christmas.domain.constants.menu;

import christmas.message.ErrorMessage;
import christmas.view.constants.OutputMessage;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

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

    public static MenuGroup findMenuCategory(String menuName) {
        return Arrays.stream(MenuGroup.values())
                .filter(menuGroup -> menuGroup.getMenuItems().stream()
                        .anyMatch(menu -> menu.getName().equals(menuName)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.MENU_NOT_FOUND.getReasonFormattedMessage()));
    }

    public MenuInterface getMenuByName(String menuName) {
        return this.getMenuItems().stream()
                .filter(item -> item.getName().equals(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.MENU_NOT_FOUND.getReasonFormattedMessage()));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OutputMessage.PREFIX.getMessage())
                .append(title)
                .append(OutputMessage.SUFFIX.getMessage())
                .append("\n");
        StringJoiner stringJoiner = new StringJoiner(OutputMessage.COMMA.getMessage() + OutputMessage.BLANK.getMessage());
        menuItems.forEach(item ->
                stringJoiner.add(item.toString())
        );
        stringBuilder.append(stringJoiner).append("\n");
        return stringBuilder.toString();
    }
}
