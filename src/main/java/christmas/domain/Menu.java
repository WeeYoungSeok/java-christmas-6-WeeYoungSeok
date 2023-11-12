package christmas.domain;

import christmas.domain.contants.menu.MenuGroup;
import christmas.domain.contants.menu.MenuInterface;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private final Map<String, Map<MenuInterface, Integer>> menus;

    public Menu(String menuInput) {
        this.menus = menuSettingAndValidate(menuInput);
    }

    public Map<String, Map<MenuInterface, Integer>> menuSettingAndValidate(String menuInput) {
        Map<String, Map<MenuInterface, Integer>> menuGroups = new HashMap<>();
        Arrays.stream(menuInput.split(","))
                .forEach(menuNameAndCount -> {
                    String menuName = menuNameAndCount.split("-")[0];
                    String menuCount = menuNameAndCount.split("-")[1];
                    menuGroups.computeIfAbsent(validateInvalidMenuName(menuName).getTitle(), k -> new HashMap<>())
                            .put(MenuGroup.findMenu(menuName), Integer.parseInt(menuCount));
                });
        return menuGroups;
    }

    public MenuGroup validateInvalidMenuName(String menuName) {
        return MenuGroup.findMenuCategory(menuName);
    }
}
