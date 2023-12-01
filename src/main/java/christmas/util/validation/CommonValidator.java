package christmas.util.validation;

import christmas.message.ErrorMessage;

public class CommonValidator {

    public static void isBlank(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.BLANK.getReasonFormattedMessage());
        }
    }
}
