package christmas.view;

import christmas.domain.Event;
import christmas.domain.Menu;
import christmas.view.contants.OutputMessage;

public class OutputView {

    public static void printStart() {
        System.out.println(OutputMessage.START.getMessage());
    }

    public static void printMenu(Menu menu) {
        System.out.println(OutputMessage.ORDER_MENU.getFormattedMessage());
        System.out.println(menu);
        System.out.println();
    }

    public static void printBeforeDiscountMenuTotalPrice(Menu menu) {
        System.out.println(OutputMessage.BEFORE_DISCOUNT_TOTAL_PRICE.getFormattedMessage());
        System.out.println(String.format(OutputMessage.PRICE.getMessage(), menu.getTotalMenuPrice()));
        System.out.println();
    }

    public static void printGift(Event event) {
        System.out.println(OutputMessage.GIFT_MENU.getFormattedMessage());
        StringBuilder stringBuilder = new StringBuilder();
        event.getGifts().forEach((key, value) -> {
            stringBuilder.append(key.getName());
            if (!stringBuilder.toString().contains("없음")) {
                stringBuilder.append(" ").append(key.getCount()).append("개");
            }
        });
        System.out.println(stringBuilder);
        System.out.println();
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
