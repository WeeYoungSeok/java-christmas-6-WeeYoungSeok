package christmas.message;

public enum ErrorMessage {
    PREFIX("[ERROR] "),
    SUFFIX(" 다시 입력해 주세요."),
    INVALID_DATE("유효하지 않은 날짜입니다."),
    INVALID_ORDER("유효하지 않는 주문입니다."),
    INVALID_MENU_COUNT("메뉴의 개수는 숫자만 가능합니다."),
    INVALID_MINIMUM_MENU_COUNT("메뉴의 개수는 1 이상의 숫자만 입력되도록 해주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getFormattedMessage() {
        return PREFIX.message + message + SUFFIX.message;
    }

    public String getMessage() {
        return this.message;
    }
}
