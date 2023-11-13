package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.NumericConverter;
import christmas.util.contants.RegularExpression;
import christmas.util.validation.CommonValidator;
import christmas.view.contants.InputMessage;

public class InputView {
    private final String regTest = "(?:[^,]+-[^,-]+,)*[^,]+-[^,-]+";
    private static final NumericConverter numericConverter = new NumericConverter();

    public static int readDate() {
        System.out.println(InputMessage.VISIT_DATE.getMessage());
        String inputDate = Console.readLine();
        CommonValidator.isBlank(inputDate);
        return numericConverter.convert(inputDate.replace(" ", ""));
    }

    public static String readMenu() {
        System.out.println(InputMessage.ORDER_MENU.getMessage());
        String inputMenu = Console.readLine();
        System.out.println();
        CommonValidator.isBlank(inputMenu);
        RegularExpression.MENU_REGEX.validateMatch(inputMenu.replace(" ", ""));
        return inputMenu.replace(" ", "");
    }
}
