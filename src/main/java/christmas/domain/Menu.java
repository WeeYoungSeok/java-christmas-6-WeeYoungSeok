package christmas.domain;

import christmas.domain.contants.menu.MenuGroup;
import christmas.domain.contants.menu.MenuInterface;
import christmas.message.ErrorMessage;
import christmas.util.NumericConverter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {
    private final Map<String, Map<MenuInterface, Integer>> menus;

    public Menu(String menuInput) {
        validateDuplicateMenu(menuInput);
        this.menus = menuSettingAndValidate(menuInput);
    }

    public Map<String, Map<MenuInterface, Integer>> menuSettingAndValidate(String menuInput) {
        Map<String, Map<MenuInterface, Integer>> menuGroups = new HashMap<>();
        Arrays.stream(menuInput.split(","))
                .forEach(menuNameAndCount -> {
                    String menuName = menuNameAndCount.split("-")[0];
                    String menuCount = menuNameAndCount.split("-")[1];
                    menuGroups.computeIfAbsent(validateInvalidMenuName(menuName).getTitle(), k -> new HashMap<>())
                            .put(MenuGroup.findMenu(menuName), validateMenuCount(menuCount));
                });
        return menuGroups;
    }

    public MenuGroup validateInvalidMenuName(String menuName) {
        return MenuGroup.findMenuCategory(menuName);
    }

    public int validateMenuCount(String menuCount) {
        NumericConverter numericConverter = new NumericConverter();
        try {
            return validateMenuCountIsPositive(numericConverter.convert(menuCount));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    ErrorMessage.INVALID_ORDER.getFormattedMessage() +
                            "\n" +
                            ErrorMessage.INVALID_MENU_COUNT.getMessage()
            );
        }
    }

    public int validateMenuCountIsPositive(int menuCount) {
        if (menuCount <= 0) {
            throw new IllegalArgumentException(
                    ErrorMessage.INVALID_ORDER.getFormattedMessage() +
                            "\n" +
                            ErrorMessage.INVALID_MINIMUM_MENU_COUNT.getMessage()
            );
        }
        return menuCount;
    }

    public void validateDuplicateMenu(String menuName) {
        List<String> menuNames = Arrays.stream(menuName.split(","))
                .map(menuNameAndCount -> menuNameAndCount.split("-")[0].trim())
                .toList();
        if (menuNames.size() != menuNames.stream().distinct().count()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER +
                    "\n" +
                    ErrorMessage.INVALID_DUPLICATE_MENU.getReasonFormattedMessage());
        }
    }
}
