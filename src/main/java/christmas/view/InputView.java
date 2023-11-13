package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.NumericConverter;
import christmas.util.validation.CommonValidator;
import christmas.view.contants.InputMessage;

public class InputView {
    private static final NumericConverter numericConverter = new NumericConverter();

    public static int readDate() {
        System.out.println(InputMessage.VISIT_DATE.getMessage());
        String inputDate = Console.readLine();
        CommonValidator.isBlank(inputDate);
        return numericConverter.convert(inputDate);
    }
}
