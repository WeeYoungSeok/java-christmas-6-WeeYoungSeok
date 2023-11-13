package christmas.domain;

import christmas.domain.contants.menu.MenuGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class MenuTest {

    @ParameterizedTest
    @DisplayName("유효하지 않는 주문이 들어올 경우 예외 발생")
    @ValueSource(strings = {"비빔밥-1,콜라-1", "안심스테이크-2,화이트와인-1,그릭샐러드-1", "초코아이스크림-1"})
    void invalidOrder(String menuInput) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Menu(menuInput));
    }

    @ParameterizedTest
    @DisplayName("메뉴의 개수가 1이상의 숫자가 아니라면 예외 발생")
    @ValueSource(strings = {"해산물파스타-0,제로콜라-0", "티본스테이크-2,레드와인-0", "아이스크림-0"})
    void invalidMenuCount(String menuInput) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Menu(menuInput));
    }

    @ParameterizedTest
    @DisplayName("메뉴가 중복으로 입력된다면 예외 발생")
    @ValueSource(strings = {"해산물파스타-1,제로콜라-1,제로콜라-1", "티본스테이크-2,티본스테이크-1"})
    void invalidDuplicateMenu(String menuInput) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Menu(menuInput));
    }

    @ParameterizedTest
    @DisplayName("음료만 주문시 예외 발생")
    @ValueSource(strings = {"제로콜라-1", "제로콜라-1,레드와인-1", "제로콜라-1,레드와인-1,샴페인-1"})
    void invalidOnlyBeverage(String menuInput) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Menu(menuInput));
    }

    @ParameterizedTest
    @DisplayName("주문 메뉴가 20개가 넘으면 예외 발생")
    @ValueSource(strings = {"해산물파스타-10,제로콜라-21", "티본스테이크-11,제로콜라-10", "크리스마스파스타-5,아이스크림-10,제로콜라-5,양송이수프-1"})
    void menuCountLimits(String menuInput) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Menu(menuInput));
    }

    @ParameterizedTest
    @DisplayName("총주문 금액 계산")
    @MethodSource("menuSetting")
    void calculateTotalMenuPrice(Menu menu, int totalPrice) {
        assertThat(menu.getTotalMenuPrice()).isEqualTo(totalPrice);
    }

    @ParameterizedTest
    @DisplayName("총주문 금액이 10000원 이상이라면 true 반환")
    @MethodSource("menuSetting")
    void totalPriceTenThousandOrMore(Menu menu) {
        assertThat(menu.isTotalPriceTenThousandOrMore()).isTrue();
    }

    @Test
    @DisplayName("총주문 금액이 10000원 미만이라면 false 반환")
    void totalPriceTenThousandUnder() {
        assertThat(new Menu("시저샐러드-1").isTotalPriceTenThousandOrMore()).isFalse();
    }

    @ParameterizedTest
    @DisplayName("메인 메뉴 갯수 반환")
    @MethodSource("menuSetting")
    void mainMenuCount(Menu menu, int totalPrice, int mainMenuCount) {
        assertThat(menu.categoryMenuCount(MenuGroup.MAIN)).isEqualTo(mainMenuCount);
    }

    @ParameterizedTest
    @DisplayName("디저트 메뉴 갯수 반환")
    @MethodSource("menuSetting")
    void dessertMenuCount(Menu menu, int totalPrice, int dessertCount) {
        assertThat(menu.categoryMenuCount(MenuGroup.DESSERT)).isEqualTo(dessertCount);
    }

    static Stream<Arguments> menuSetting() {
        return Stream.of(
                Arguments.arguments(new Menu("초코케이크-3,해산물파스타-1,제로콜라-1,티본스테이크-2"), 193_000, 3),
                Arguments.arguments(new Menu("아이스크림-3,제로콜라-1,양송이수프-2,크리스마스파스타-3,시저샐러드-4"), 137_000, 3)
        );
    }
}
