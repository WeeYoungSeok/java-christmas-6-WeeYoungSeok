package christmas.domain;

import christmas.domain.contants.event.EventValue;
import christmas.domain.contants.menu.MenuGroup;
import christmas.domain.contants.menu.MenuInterface;
import christmas.message.ErrorMessage;
import christmas.util.NumericConverter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {
    private static final int MAX_MENU_COUNT = 20;

    private final Map<String, Map<MenuInterface, Integer>> menus;

    public Menu(String menuInput) {
        String[] menuCommaSplit = menuInput.split(",");
        validateDuplicateMenu(menuCommaSplit);
        validateMenuCountLimits(menuCommaSplit);
        this.menus = menuSettingAndValidate(menuCommaSplit);
    }

    public Map<String, Map<MenuInterface, Integer>> menuSettingAndValidate(String[] menuNameAndCountArr) {
        Map<String, Map<MenuInterface, Integer>> menuGroups = new HashMap<>();
        Arrays.stream(menuNameAndCountArr)
                .forEach(menuNameAndCount -> {
                    String menuName = menuNameAndCount.split("-")[0];
                    String menuCount = menuNameAndCount.split("-")[1];
                    menuGroups.computeIfAbsent(validateInvalidMenuName(menuName).getTitle(), k -> new HashMap<>())
                            .put(MenuGroup.findMenu(menuName), Integer.parseInt(menuCount));
                });
        validateOnlyBeverage(menuGroups);
        return menuGroups;
    }

    public MenuGroup validateInvalidMenuName(String menuName) {
        return MenuGroup.findMenuCategory(menuName);
    }

    public int validateMenuCount(String menuCount) {
        NumericConverter numericConverter = new NumericConverter();
        try {
            int count = numericConverter.convert(menuCount);
            validateMenuCountIsPositive(count);
            return count;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_COUNT.getReasonFormattedMessage());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MINIMUM_MENU_COUNT.getReasonFormattedMessage());

        }
    }

    public void validateMenuCountIsPositive(int menuCount) {
        if (menuCount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MINIMUM_MENU_COUNT.getMessage());
        }
    }

    public void validateDuplicateMenu(String[] menuNameAndCount) {
        List<String> menuNames = Arrays.stream(menuNameAndCount)
                .map(nameAndCount -> nameAndCount.split("-")[0].trim())
                .toList();
        if (menuNames.size() != menuNames.stream().distinct().count()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DUPLICATE_MENU.getReasonFormattedMessage());
        }
    }

    public void validateOnlyBeverage(Map<String, Map<MenuInterface, Integer>> menuGroups) {
        if (menuGroups.entrySet().stream()
                .allMatch(entry -> entry.getKey().equals(MenuGroup.BEVERAGE.getTitle()))) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ONLY_BEVERAGE.getReasonFormattedMessage());
        }
    }

    public void validateMenuCountLimits(String[] menuNameAndCount) {
        if (Arrays.stream(menuNameAndCount)
                .mapToInt(nameAndCount -> validateMenuCount(nameAndCount.split("-")[1]))
                .sum() > MAX_MENU_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_COUNT_LIMITS.getReasonFormattedMessage());
        }
    }

    public int getTotalMenuPrice() {
        return this.menus.values()
                .stream()
                .flatMap(menuGroup -> menuGroup.entrySet().stream())
                .mapToInt(menu -> menu.getKey().getPrice() * menu.getValue())
                .sum();
    }

    public boolean isTotalPriceTenThousandOrMore() {
        return getTotalMenuPrice() >= EventValue.ORDER_MIN_PRICE.getValue();
    }

    public int categoryMenuCount(MenuGroup categoryMenu) {
        return this.menus.entrySet()
                .stream()
                .filter(menuGroup -> menuGroup.getKey().equals(categoryMenu.getTitle()))
                .mapToInt(menuGroup -> menuGroup.getValue().values().stream().mapToInt(Integer::intValue).sum())
                .sum();
    }
}
