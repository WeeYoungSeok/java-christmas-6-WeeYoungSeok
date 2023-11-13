package christmas.controller;

import christmas.domain.EventCalendar;
import christmas.domain.Menu;
import christmas.domain.VisitDate;
import christmas.message.ErrorMessage;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasPromotionController {
    private EventCalendar eventCalendar;
    private VisitDate visitDate;
    private Menu menu;

    public void run() {
        eventCalendarSetting(2023, 12);
        visitDate();
        menuSetting();
        result();
    }

    private void eventCalendarSetting(int year, int month) {
        this.eventCalendar = new EventCalendar(year, month);
    }

    private void visitDate() {
        OutputView.printStart();
        while (true) {
            try {
                this.visitDate = VisitDate.of(InputView.readDate(), this.eventCalendar);
                return;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(ErrorMessage.INVALID_DATE.getFormattedMessage());
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    private void menuSetting() {
        while (true) {
            try {
                this.menu = new Menu(InputView.readMenu());
                return;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(ErrorMessage.INVALID_ORDER.getFormattedMessage());
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    private void result() {
        OutputView.printMenu(menu);
    }
}
