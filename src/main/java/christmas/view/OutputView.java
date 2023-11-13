package christmas.view;

import christmas.view.contants.OutputMessage;

public class OutputView {

    public static void printStart() {
        System.out.println(OutputMessage.START.getMessage());
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
