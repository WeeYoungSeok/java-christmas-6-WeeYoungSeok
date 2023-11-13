package christmas.util;

public class NumericConverter implements Converter<String, Integer> {

    private static final String ERROR_MESSAGE = "숫자가 아닙니다.";

    @Override
    public Integer convert(String target) {
        try {
            return Integer.parseInt(target);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }
}
