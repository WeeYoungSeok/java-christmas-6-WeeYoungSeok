package christmas.util.constants;

import christmas.message.ErrorMessage;

public enum RegularExpression {
    MENU_REGEX("(?:[^,-]+-[^-,]+,)*[^,-]+-[^-,]+");

    private final String pattern;

    RegularExpression(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public void validateMatch(String text) {
        if (!text.matches(pattern)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_EXAMPLE.getReasonFormattedMessage());
        }
    }
}
