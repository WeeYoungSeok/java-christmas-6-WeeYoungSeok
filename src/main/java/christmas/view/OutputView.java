package christmas.view;

import christmas.domain.Menu;
import christmas.view.contants.OutputMessage;

public class OutputView {

    public static void printStart() {
        System.out.println(OutputMessage.START.getMessage());
    }

    public static void printMenu(Menu menu) {
        System.out.println(OutputMessage.ORDER_MENU.getFormattedMessage());
        System.out.println(menu);

    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
