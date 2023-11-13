package christmas.view;

import christmas.message.ErrorMessage;
import christmas.view.contants.OutputMessage;

public class OutputView {

    public static void printStart() {
        System.out.println(OutputMessage.START.getMessage());
    }

    public static void printMenu() {
        System.out.println("<주문 메뉴>");
        // ...
    }

    public static void printVisitDateErrorMessage() {
        System.out.println(ErrorMessage.INVALID_DATE.getFormattedMessage());
    }
}
