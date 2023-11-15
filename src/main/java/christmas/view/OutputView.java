package christmas.view;

import christmas.domain.Event;
import christmas.domain.EventCalendar;
import christmas.domain.Menu;
import christmas.domain.constants.menu.MenuGroup;
import christmas.message.ErrorMessage;
import christmas.util.EventCalculate;
import christmas.view.constants.OutputMessage;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class OutputView {
    private static final EventCalculate eventCalculate = new EventCalculate();

    public static void printStart() {
        System.out.println(OutputMessage.START.getMessage());
    }

    public static void printSelectMenu(Menu menu) {
        System.out.println(OutputMessage.ORDER_MENU.getFormattedMessage());
        System.out.println(menu);
        System.out.println();
    }

    public static void printAllMenu(String errorMessage) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        if (errorMessage.contains(ErrorMessage.MENU_NOT_FOUND.getMessage())) {
            stringJoiner.add(OutputMessage.EMPTY.getMessage());
            stringJoiner.add(OutputMessage.ORDER_LIST.getMessage());
            stringJoiner.add(OutputMessage.EMPTY.getMessage());
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
        event.getGifts().forEach((key, value) -> stringBuilder.append(key.getName())
                .append(OutputMessage.BLANK.getMessage())
                .append(String.format(OutputMessage.COUNT.getMessage(), key.getCount())));
        addBuilderNone(stringBuilder);
        System.out.println(stringBuilder);
        System.out.println();
    }

    public static void printBenefitHistory(Event event) {
        System.out.println(OutputMessage.BENEFITS_HISTORY.getFormattedMessage());
        printBenefits(event);
        System.out.println();
    }

    private static void printBenefits(Event event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                event.getEventDiscountGroup().entrySet().stream()
                        .filter(entry -> entry.getValue() != 0)
                        .map(entry -> entry.getKey().getDiscountName() +
                                OutputMessage.COLON.getMessage() + OutputMessage.BLANK.getMessage() +
                                String.format(OutputMessage.PRICE.getMessage(), eventCalculate.toNegative(entry.getValue())))
                        .collect(Collectors.joining("\n"))
        );
        addBuilderNone(stringBuilder);
        System.out.println(stringBuilder);
    }

    public static void printBenefitsAllAmount(Event event) {
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
        System.out.print(event.getBadge());
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void addBuilderNone(StringBuilder stringBuilder) {
        if (stringBuilder.isEmpty()) {
            stringBuilder.append(OutputMessage.NONE.getMessage());
        }
    }
}
