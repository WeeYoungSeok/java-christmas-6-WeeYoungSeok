package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class VisitDateTest {
    private final int year = 2023;
    private final Month month = Month.of(12);
    private final int firstDay = 1;
    private int endDay;

    @BeforeEach
    void setUp() {
        LocalDate localDate = LocalDate.of(year, month, 1);
        this.endDay = localDate.lengthOfMonth();
    }

    @ParameterizedTest
    @DisplayName("입력한 날짜가 해당 월의 날짜 범위 안에 없다면 예외 발생")
    @ValueSource(ints = {-1, -2, 0, 32, 33, 34})
    void outOfRangeVisitDay(int visitDay) {
        assertThatIllegalArgumentException().isThrownBy(() -> VisitDate.of(visitDay, this.firstDay, this.endDay));
    }
}
