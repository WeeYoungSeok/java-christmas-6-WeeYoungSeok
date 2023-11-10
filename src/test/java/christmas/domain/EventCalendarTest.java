package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventCalendarTest {

    @ParameterizedTest
    @DisplayName("입력한 날짜가 해당 년도, 해당 월의 주말(금요일, 토요일)이라면 true 반환")
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void validateWeekendTrue(int day) {
        Assertions.assertThat(new EventCalendar(2023, 12).isWeekend(day)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("입력한 날짜가 해당 년도, 해당 월의 주말(금요일, 토요일)이라면 false 반환")
    @ValueSource(ints = {3, 4, 5, 11, 12, 19, 27})
    void validateWeekendFalse(int day) {
        Assertions.assertThat(new EventCalendar(2023, 12).isWeekend(day)).isFalse();
    }
}
