package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
}
