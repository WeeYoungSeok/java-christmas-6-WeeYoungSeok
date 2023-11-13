package christmas.domain;

import christmas.domain.contants.EventDiscount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class EventTest {
    private static EventCalendar eventCalendar = new EventCalendar(2023, 12);;

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
}
