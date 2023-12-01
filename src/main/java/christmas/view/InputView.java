package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.NumericConverter;
import christmas.util.constants.RegularExpression;
import christmas.util.validation.CommonValidator;
import christmas.view.constants.InputMessage;
import christmas.view.constants.OutputMessage;

public class InputView {
    private static final NumericConverter numericConverter = new NumericConverter();

    public static int readDate() {
        System.out.println(InputMessage.VISIT_DATE.getMessage());
        String inputDate = removeBlankFromInput();
        CommonValidator.isBlank(inputDate);
        return numericConverter.convert(inputDate);
    }

    public static String readMenu() {
        System.out.println(InputMessage.ORDER_MENU.getMessage());
        String inputMenu = removeBlankFromInput();
        System.out.println();
        CommonValidator.isBlank(inputMenu);
        RegularExpression.MENU_REGEX.validateMatch(inputMenu);
        return inputMenu;
    }

    public static String removeBlankFromInput() {
        return Console.readLine().replace(OutputMessage.BLANK.getMessage(), OutputMessage.EMPTY.getMessage());
    }
}
