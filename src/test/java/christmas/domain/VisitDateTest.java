package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class VisitDateTest {

    @ParameterizedTest
    @DisplayName("입력한 날짜가 해당 월의 날짜 범위 안에 없다면 예외 발생")
    @ValueSource(ints = {-1, -2, 0, 32, 33, 34})
    void outOfRangeVisitDay(int visitDay) {
        assertThatIllegalArgumentException().isThrownBy(() -> VisitDate.of(visitDay));
    }
}
