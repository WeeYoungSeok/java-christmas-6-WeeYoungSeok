package christmas.view.constants;

public enum OutputMessage {
    EMPTY(""),
    START("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    PREFIX("<"),
    SUFFIX(">"),
    COLON(":"),
    BLANK(" "),
    COMMA(","),
    HYPHEN("-"),
    COUNT("%s개"),
    NONE("없음"),
    ORDER_MENU("주문 메뉴"),
    ORDER_LIST("메뉴 목록은 다음과 같습니다"),
    BEFORE_DISCOUNT_TOTAL_PRICE("할인 전 총주문 금액"),
    PRICE("%,d원"),
    GIFT_MENU("증정 메뉴"),
    BENEFITS_HISTORY("혜택 내역"),
    TOTAL_BENEFITS_AMOUNT("총혜택 금액"),
    AFTER_DISCOUNT_EXPECTED_PAY_AMOUNT("할인 후 예상 결제 금액"),
    EVENT_BADGE("%s월 이벤트 배지");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getFormattedMessage() {
        return PREFIX.message + message + SUFFIX.message;
    }
}
