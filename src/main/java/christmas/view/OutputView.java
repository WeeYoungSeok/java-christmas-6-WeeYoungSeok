package christmas.view;

import christmas.domain.Event;
import christmas.domain.Menu;
import christmas.view.contants.OutputMessage;

import java.util.StringJoiner;

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
            stringBuilder.append(key.getName())
                    .append(" ").append(key.getCount()).append("개");
        });
        if (stringBuilder.isEmpty()) {
            stringBuilder.append("없음");
        }
        System.out.println(stringBuilder);
        System.out.println();
    }

    public static void printBenefits(Event event) {
        System.out.println(OutputMessage.BENEFITS_HISTORY.getFormattedMessage());
        printDiscount(event);
        printGiftBenefits(event);
        System.out.println();
    }

    public static void printDiscount(Event event) {
        StringBuilder stringBuilder = new StringBuilder();
        StringJoiner stringJoiner = new StringJoiner("\n");
        event.getEventDiscountGroup().entrySet()
                .stream().filter(entry -> entry.getValue() != 0)
                .forEach(entry-> stringJoiner.add(
                        entry.getKey().getDiscountName() + ": -" + String.format(OutputMessage.PRICE.getMessage(), entry.getValue())
                ));
        stringBuilder.append(stringJoiner);
        if (stringBuilder.isEmpty()) {
            stringBuilder.append("없음");
        }
        System.out.println(stringBuilder);
    }

    public static void printGiftBenefits(Event event) {
        StringBuilder stringBuilder = new StringBuilder();
        StringJoiner stringJoiner = new StringJoiner("\n");
        event.getGifts().entrySet()
                .stream().filter(entry -> entry.getValue() != 0)
                .forEach(entry-> stringJoiner.add(
                        OutputMessage.GIFT_EVENT.getMessage() + "-" + String.format(OutputMessage.PRICE.getMessage(), entry.getValue())
                ));
        stringBuilder.append(stringJoiner);
        if (!stringBuilder.isEmpty()) {
            System.out.println(stringBuilder);
        }
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
