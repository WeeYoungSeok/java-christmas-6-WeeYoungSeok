package christmas.domain;

import christmas.domain.contants.menu.MenuInterface;

import java.util.Map;

public class Menu {
    private final Map<MenuInterface, Integer> menus;

    public Menu(String menuInput) {
        validate(menuInput);
        this.menus = null;
    }

    public void validate(String menuInput) {
        validateInvalidMenuName(menuInput);
    }

    public void validateInvalidMenuName(String menuInput) {
        
    }
}
