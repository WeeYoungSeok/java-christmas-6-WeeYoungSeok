package christmas.message;

public enum ErrorMessage {
    PREFIX("[ERROR] "),
    SUFFIX(" 다시 입력해 주세요."),
    INVALID_DATE("유효하지 않은 날짜입니다."),
    INVALID_ORDER("유효하지 안는 주문입니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.message + message + SUFFIX.message;
    }
}