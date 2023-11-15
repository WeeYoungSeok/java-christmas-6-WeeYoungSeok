package christmas.domain;

import christmas.domain.contants.Badge;
import christmas.domain.contants.Gift;
import christmas.domain.contants.event.EventDiscount;
import christmas.domain.contants.event.EventValue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public class EventTest {
    private static final EventCalendar eventCalendar = new EventCalendar(2023, 12);
    private static StarDate starDate;

    @BeforeEach
    void starDateSetting() {
        starDate =  new StarDate(eventCalendar);
    }

    @ParameterizedTest
    @DisplayName("입력 받은 날짜가 크리스마스 디데이 기간이라면 true")
    @MethodSource("christmasDDaySetting")
    void isChristmasDDay(VisitDate visitDate, Menu menu) {
        Assertions.assertThat(new Event().isVisitDateChristmasDDay(visitDate, eventCalendar)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("입력 받은 날짜가 크리스마스 디데이 기간을 계산하여 할인값 계산")
    @MethodSource("christmasDDaySetting")
    void isChristmasDDayDiscount(VisitDate visitDate, Menu menu, int discount) {
        Event event = new Event();
        event.eventSetting(visitDate, eventCalendar, starDate, menu);
        Assertions.assertThat(event.christmasDDayCalculate(visitDate)).isEqualTo(discount);
    }

    static Stream<Arguments> christmasDDaySetting() {
        return Stream.of(
                Arguments.arguments(
                        VisitDate.of(18, eventCalendar),
                        new Menu("초코케이크-3,티본스테이크-2"),
                        EventDiscount.CHRISTMAS.getDiscount() +
                                (
                                        (18 - EventValue.CHRISTMAS_START_DAY.getValue()) *
                                                EventValue.CHRISTMAS_ON_THE_RISE_FORM_DAY.getValue()
                                )
                ),
                Arguments.arguments(VisitDate.of(
                                23, eventCalendar),
                        new Menu("아이스크림-2,해산물파스타-2"),
                        EventDiscount.CHRISTMAS.getDiscount() +
                                (
                                        (23 - EventValue.CHRISTMAS_START_DAY.getValue()) *
                                                EventValue.CHRISTMAS_ON_THE_RISE_FORM_DAY.getValue()
                                )
                )
        );
    }

    @ParameterizedTest
    @DisplayName("입력 받은 날짜가 1 ~ 31일 사이라면 true 반환")
    @MethodSource("weekdaySetting")
    void isVisitDateAllDayEvent(VisitDate visitDate, Menu menu) {
        Event event = new Event();
        Assertions.assertThat(event.isVisitDateAllDateEvent(visitDate, eventCalendar)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("입력 받은 날짜가 평일이라면 디저트 메뉴 1개당 2023원 할인 적용")
    @MethodSource("weekdaySetting")
    void isWeekdayDiscount(VisitDate visitDate, Menu menu, int discount) {
        Event event = new Event();
        event.eventSetting(visitDate, eventCalendar, starDate, menu);
        Assertions.assertThat(event.getEventDiscountGroup().entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(EventDiscount.WEEKDAY))
                .mapToInt(Map.Entry::getValue)
                .sum()).isEqualTo(discount);
    }

    static Stream<Arguments> weekdaySetting() {
        return Stream.of(
                Arguments.arguments(
                        VisitDate.of(10, eventCalendar),
                        new Menu("초코케이크-3,티본스테이크-2"),
                        EventDiscount.WEEKDAY.getDiscount() * 3
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
        Event event = new Event();
        event.eventSetting(visitDate, eventCalendar, starDate, menu);
        Assertions.assertThat(event.getEventDiscountGroup().entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(EventDiscount.WEEKEND))
                .mapToInt(Map.Entry::getValue)
                .sum()).isEqualTo(discount);
    }

    static Stream<Arguments> weekendSetting() {
        return Stream.of(
                Arguments.arguments(
                        VisitDate.of(1, eventCalendar),
                        new Menu("초코케이크-3,티본스테이크-5"),
                        EventDiscount.WEEKEND.getDiscount() * 5
                ),
                Arguments.arguments(VisitDate.of(
                                16, eventCalendar),
                        new Menu("아이스크림-2,해산물파스타-4"),
                        EventDiscount.WEEKEND.getDiscount() * 4
                )
        );
    }

    @ParameterizedTest
    @DisplayName("입력 받은 날짜가 별이 있다면 1000원 특별 할인 적용")
    @MethodSource("specialArgumentsSetting")
    void isSpecialDiscount(VisitDate visitDate, Menu menu, int discount) {
        Event event = new Event();
        event.eventSetting(visitDate, eventCalendar, starDate, menu);
        Assertions.assertThat(event.getEventDiscountGroup().entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(EventDiscount.SPECIAL))
                .mapToInt(Map.Entry::getValue)
                .sum()).isEqualTo(discount);
    }

    static Stream<Arguments> specialArgumentsSetting() {
        return Stream.of(
                Arguments.arguments(
                        VisitDate.of(3, eventCalendar),
                        new Menu("초코케이크-3,티본스테이크-5"),
                        EventDiscount.SPECIAL.getDiscount()
                ),
                Arguments.arguments(VisitDate.of(
                                10, eventCalendar),
                        new Menu("아이스크림-2,해산물파스타-4"),
                        EventDiscount.SPECIAL.getDiscount()
                )
        );
    }

    @ParameterizedTest
    @DisplayName("총주문 금액 120_000원 이상이라면 샴페인 증정")
    @MethodSource("giftArgumentsSetting")
    void isGiftFromMenuTotalPrice(Menu menu, Gift expectedGift) {
        org.junit.jupiter.api.Assertions.assertEquals(
                expectedGift,
                Arrays.stream(Gift.values())
                        .filter(giftEnum -> giftEnum.isGiftApplicable(menu.getTotalMenuPrice()))
                        .findFirst()
                        .orElse(null)
        );
    }

    static Stream<Arguments> giftArgumentsSetting() {
        return Stream.of(
                Arguments.arguments(
                        new Menu("초코케이크-3,티본스테이크-5"),
                        Gift.CHAMPAGNE
                ),
                Arguments.arguments(
                        new Menu("아이스크림-2,해산물파스타-4"),
                        Gift.CHAMPAGNE
                )
        );
    }

    @ParameterizedTest
    @DisplayName("할인 후 예상 결제 금액을 계산할 때 증정 이벤트는 제외된 값을 계산")
    @MethodSource("EventSettingForAfterPayAmount")
    void afterPayAmount(VisitDate visitDate, Menu menu, int afterPayAmount) {
        Event event = new Event();
        event.eventSetting(visitDate, eventCalendar, starDate, menu);
        Assertions.assertThat(event.getTotalEventDiscount()).isEqualTo(
                menu.getTotalMenuPrice() - afterPayAmount);
    }

    static Stream<Arguments> EventSettingForAfterPayAmount() {
        return Stream.of(
                Arguments.arguments(
                        VisitDate.of(3, eventCalendar),
                        new Menu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"),
                        135754
                ),
                Arguments.arguments(VisitDate.of(
                                2, eventCalendar),
                        new Menu("양송이수프-2,티본스테이크-2,바비큐립-1,초코케이크-2,제로콜라-1"),
                        201831
                )
        );
    }

    @ParameterizedTest
    @DisplayName("총혜택 금액에 맞는 배지를 부여한다.")
    @MethodSource("EventSettingForBadge")
    void validateBadgeForBenefits(VisitDate visitDate, Menu menu, Badge exceptBadge) {
        Event event = new Event();
        event.eventSetting(visitDate, eventCalendar, starDate, menu);
        Assertions.assertThat(event.getBadge()).isEqualTo(
                exceptBadge);
    }

    static Stream<Arguments> EventSettingForBadge() {
        return Stream.of(
                Arguments.arguments(
                        VisitDate.of(25, eventCalendar),
                        new Menu("양송이수프-1"),
                        Badge.NONE
                ),
                Arguments.arguments(VisitDate.of(
                                23, eventCalendar),
                        new Menu("티본스테이크-1"),
                        Badge.START
                ),
                Arguments.arguments(VisitDate.of(
                                24, eventCalendar),
                        new Menu("초코케이크-3"),
                        Badge.TREE
                ),
                Arguments.arguments(VisitDate.of(
                                3, eventCalendar),
                        new Menu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"),
                        Badge.SANTA
                )
        );
    }
}
