package christmas.view;

import christmas.domain.Event;
import christmas.domain.EventCalendar;
import christmas.domain.Menu;
import christmas.domain.contants.Badge;
import christmas.domain.contants.menu.MenuGroup;
import christmas.message.ErrorMessage;
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

    public static void printMenuSelect(Menu menu) {
        System.out.println(OutputMessage.ORDER_MENU.getFormattedMessage());
        System.out.println(menu);
        System.out.println();
    }

    public static void printMenuList(String errorMessage) {
        if (errorMessage.contains(ErrorMessage.MENU_NOT_FOUND.getMessage())) {
            stringJoiner.add("");
            stringJoiner.add(OutputMessage.ORDER_LIST.getMessage());
            stringJoiner.add("");
            Arrays.stream(MenuGroup.values())
                    .forEach(menuGroup -> stringJoiner.add(menuGroup.toString()));
            System.out.print(stringJoiner);
        }
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
                    .append(OutputMessage.BLANK.getMessage()).append(key.getCount()).append("개");
        });
        addBuilderNone();
        System.out.println(stringBuilder);
        System.out.println();
    }

    public static void printBenefits(Event event) {
        System.out.println(OutputMessage.BENEFITS_HISTORY.getFormattedMessage());
        printDiscount(event);
        addBuilderNone();
        System.out.println(stringBuilder);
        System.out.println();
    }

    public static void printDiscount(Event event) {
        event.getEventDiscountGroup().entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .forEach(entry -> stringJoiner.add(
                        entry.getKey().getDiscountName() + OutputMessage.COLON.getMessage() + OutputMessage.BLANK.getMessage() +
                                String.format(OutputMessage.PRICE.getMessage(), eventCalculate.toNegative(entry.getValue()))
                ));
        stringBuilder.append(stringJoiner);
    }

    public static void printBenefitsAmount(Event event) {
        System.out.println(OutputMessage.TOTAL_BENEFITS_AMOUNT.getFormattedMessage());
        System.out.println(String.format(
                OutputMessage.PRICE.getMessage(),
                eventCalculate.toNegative(
                        eventCalculate.plus(
                                event.getTotalEventDiscount(),
                                event.getTotalGiftAmount()
                        )
                )
        ));
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

    public static void addBuilderNone() {
        if (stringBuilder.isEmpty()) {
            stringBuilder.append(OutputMessage.NONE.getMessage());
        }
    }
}
