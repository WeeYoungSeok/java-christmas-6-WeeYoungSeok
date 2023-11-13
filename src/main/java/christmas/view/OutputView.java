package christmas.view;

import christmas.domain.Event;
import christmas.domain.EventCalendar;
import christmas.domain.Menu;
import christmas.domain.contants.Badge;
import christmas.util.EventCalculate;
import christmas.view.contants.OutputMessage;

import java.util.Arrays;
import java.util.StringJoiner;

public class OutputView {

    private static StringBuilder stringBuilder = new StringBuilder();
    private static StringJoiner stringJoiner = new StringJoiner("\n");
    private static EventCalculate eventCalculate = new EventCalculate();

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
            stringBuilder.append(OutputMessage.NONE.getMessage());
        }
        System.out.println(stringBuilder);
        System.out.println();
    }

    public static void printBenefits(Event event) {
        System.out.println(OutputMessage.BENEFITS_HISTORY.getFormattedMessage());
        printDiscount(event);
        builderAndJoinerReset();
        printGiftBenefits(event);
        System.out.println();
    }

    public static void printDiscount(Event event) {
        event.getEventDiscountGroup().entrySet()
                .stream().filter(entry -> entry.getValue() != 0)
                .forEach(entry-> stringJoiner.add(
                        entry.getKey().getDiscountName() +
                                OutputMessage.COLON.getMessage() + " " + OutputMessage.MINUS.getMessage() +
                                String.format(OutputMessage.PRICE.getMessage(), entry.getValue())
                ));
        stringBuilder.append(stringJoiner);
        if (stringBuilder.isEmpty()) {
            stringBuilder.append(OutputMessage.NONE.getMessage());
        }
        System.out.println(stringBuilder);
    }

    public static void printGiftBenefits(Event event) {
        event.getGifts().entrySet()
                .stream().filter(entry -> entry.getValue() != 0)
                .forEach(entry-> stringJoiner.add(
                        OutputMessage.GIFT_EVENT.getMessage() +
                                OutputMessage.COLON.getMessage() + " " + OutputMessage.MINUS.getMessage() +
                                String.format(OutputMessage.PRICE.getMessage(), entry.getValue())
                ));
        stringBuilder.append(stringJoiner);
        if (!stringBuilder.isEmpty()) {
            System.out.println(stringBuilder);
        }
    }

    public static void printBenefitsAmount(Event event) {
        System.out.println(OutputMessage.TOTAL_BENEFITS_AMOUNT.getFormattedMessage());
        System.out.println(
                OutputMessage.MINUS.getMessage() +
                        String.format(
                                OutputMessage.PRICE.getMessage(),
                                eventCalculate.plus(event.getTotalEventDiscount(), event.getTotalGiftAmount())
                        )
        );
        System.out.println();
    }

    public static void printAfterDiscount(Menu menu, Event event) {
        System.out.println(OutputMessage.AFTER_DISCOUNT_EXPECTED_PAY_AMOUNT.getFormattedMessage());
        System.out.println(
                String.format(
                        OutputMessage.PRICE.getMessage(),
                        eventCalculate.minus(menu.getTotalMenuPrice(), event.getTotalEventDiscount())
                )
        );
        System.out.println();
    }

    public static void printBadge(Event event, EventCalendar eventCalendar) {
        System.out.println(String.format(OutputMessage.EVENT_BADGE.getFormattedMessage(), eventCalendar.getMonth()));
        System.out.print(Arrays.stream(Badge.values())
                .filter(badge -> badge.getWhichBadgeFromAmount(event.getTotalBenefitsAmount()))
                .findFirst()
                .orElse(Badge.NONE));
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void builderAndJoinerReset() {
        stringBuilder = new StringBuilder();
        stringJoiner = new StringJoiner("\n");
    }
}
