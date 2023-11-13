package christmas.controller;

import christmas.domain.EventCalendar;
import christmas.domain.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasPromotionController {
    private EventCalendar eventCalendar;
    private VisitDate visitDate;

    public void run() {
        eventCalendarSetting(2023, 12);
        visitDate();
    }

    public void eventCalendarSetting(int year, int month) {
        this.eventCalendar = new EventCalendar(year, month);
    }

    public void visitDate() {
        OutputView.printStart();
        while (true) {
            try {
                this.visitDate = VisitDate.of(InputView.readDate(), this.eventCalendar);
                return;
            } catch (IllegalArgumentException e) {
                OutputView.printVisitDateErrorMessage();
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }
}
