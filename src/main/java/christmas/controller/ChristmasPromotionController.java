package christmas.controller;

import christmas.domain.*;
import christmas.message.ErrorMessage;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasPromotionController {
    private EventCalendar eventCalendar;
    private VisitDate visitDate;
    private Menu menu;
    private Event event;
    private StarDate starDate;

    public void run() {
        eventCalendarSetting(2023, 12);
        starDateSetting();
        visitDate();
        menuSetting();
        result();
    }

    private void eventCalendarSetting(int year, int month) {
        this.eventCalendar = new EventCalendar(year, month);
    }

    private void starDateSetting() {
        this.starDate = new StarDate(eventCalendar);
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
        OutputView.printBeforeDiscountMenuTotalPrice(menu);
        this.event = new Event(visitDate, eventCalendar, menu, starDate);
        OutputView.printGift(event);
        OutputView.printBenefits(event);
        OutputView.printAfterDiscount(menu, event);
    }
}
