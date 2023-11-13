package christmas.domain;

import christmas.domain.contants.event.EventDiscount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class EventTest {
    private static EventCalendar eventCalendar = new EventCalendar(2023, 12);;

    @ParameterizedTest
    @DisplayName("입력 받은 날짜가 크리스마스 디데이 기간이라면 true")
    @MethodSource("weekdaySetting")
    void isChristmasDDay(VisitDate visitDate, Menu menu) {
        Assertions.assertThat(new Event(visitDate, eventCalendar, menu).isVisitDateChristmasDDay(visitDate, eventCalendar)).isTrue();
    }

    static Stream<Arguments> christmasDDaySetting() {
        return Stream.of(
                Arguments.arguments(
                        VisitDate.of(18, eventCalendar),
                        new Menu("초코케이크-3,티본스테이크-2")
                ),
                Arguments.arguments(VisitDate.of(
                                23, eventCalendar),
                        new Menu("아이스크림-2,해산물파스타-2")
                )
        );
    }

    @ParameterizedTest
    @DisplayName("입력 받은 날짜가 평일이라면 디저트 메뉴 1개당 2023원 할인 적용")
    @MethodSource("weekdaySetting")
    void isWeekdayDiscount(VisitDate visitDate, Menu menu, int discount) {
        Assertions.assertThat(new Event(visitDate, eventCalendar, menu).getWeekdayDiscount()).isEqualTo(discount);
    }

    static Stream<Arguments> weekdaySetting() {
        return Stream.of(
                Arguments.arguments(
                        VisitDate.of(18, eventCalendar),
                        new Menu("초코케이크-3,티본스테이크-2")
                        , EventDiscount.WEEKDAY.getDiscount() * 3
                ),
                Arguments.arguments(VisitDate.of(
                        27, eventCalendar),
                        new Menu("아이스크림-2,해산물파스타-2"),
                        EventDiscount.WEEKDAY.getDiscount() * 2
                )
        );
    }

    @ParameterizedTest
    @DisplayName("입력 받은 날짜가 주말이라면 메인 메뉴 1개당 2023원 할인 적용")
    @MethodSource("weekendSetting")
    void isWeekendDiscount(VisitDate visitDate, Menu menu, int discount) {
        Assertions.assertThat(new Event(visitDate, eventCalendar, menu).getWeekendDiscount()).isEqualTo(discount);
    }

    static Stream<Arguments> weekendSetting() {
        return Stream.of(
                Arguments.arguments(
                        VisitDate.of(1, eventCalendar),
                        new Menu("초코케이크-3,티본스테이크-5")
                        , EventDiscount.WEEKEND.getDiscount() * 5
                ),
                Arguments.arguments(VisitDate.of(
                                16, eventCalendar),
                        new Menu("아이스크림-2,해산물파스타-4"),
                        EventDiscount.WEEKEND.getDiscount() * 4
                )
        );
    }
}
