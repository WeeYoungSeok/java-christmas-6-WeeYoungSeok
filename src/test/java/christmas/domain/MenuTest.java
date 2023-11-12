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
}
