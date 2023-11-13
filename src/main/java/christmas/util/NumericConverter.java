package christmas.util;

import christmas.message.ErrorMessage;

public class NumericConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String target) {
        try {
            return Integer.parseInt(target);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER.getReasonFormattedMessage());
        }
    }
}
