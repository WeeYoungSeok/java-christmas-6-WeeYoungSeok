package christmas.message;

public enum ErrorMessage {
    PREFIX("[ERROR] "),
    SUFFIX(" 다시 입력해 주세요."),
    REASON("이유 : %s"),
    BLANK("빈 값은 입력 불가능합니다."),
    INVALID_DATE("유효하지 않은 날짜입니다."),
    INVALID_ORDER("유효하지 않은 주문입니다."),
    INVALID_MENU_COUNT("메뉴의 개수는 숫자만 가능합니다."),
    INVALID_MINIMUM_MENU_COUNT("메뉴의 개수는 1 이상의 숫자만 입력되도록 해주세요."),
    INVALID_DUPLICATE_MENU("중복된 메뉴는 입력 불가능 합니다."),
    INVALID_MENU_COUNT_LIMITS("메뉴의 개수는 20개 이하만 가능합니다."),
    INVALID_NUMBER("숫자가 아닙니다."),
    OUT_OF_RANGE_DATE("%s ~ %s일 사이의 숫자만 입력 부탁드립니다."),
    INVALID_EXAMPLE("예시와 맞지 않습니다."),
    MENU_NOT_FOUND("메뉴에 없는 항목입니다."),
    INVALID_ONLY_BEVERAGE("음료만 주문 불가능합니다.");

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

    public String getReasonFormattedMessage() {
        return String.format(REASON.message, this.message);
    }
}
