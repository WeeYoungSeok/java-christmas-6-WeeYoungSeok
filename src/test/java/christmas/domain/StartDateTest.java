package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

public class StartDateTest {

    private List<Integer> starDates;

    @BeforeEach
    public void setUp() {
        this.starDates = Arrays.asList(3, 10, 17, 24, 25, 31);
    }

    @ParameterizedTest
    @DisplayName("입력한 날짜가 해당 년도, 해당 월의 날짜 중 별이 있는 날짜와 일치한다면 true 반환")
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void dateMatchesWithStar(int day) {
        Assertions.assertThat(new StarDate(this.starDates).visitDateContainsStarDates(day)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("입력한 날짜가 해당 년도, 해당 월의 날짜 중 별이 있는 날짜와 일치하지 않는다면 false 반환")
    @ValueSource(ints = {1, 6, 12, 22, 23, 30})
    void dateDoesNotMatchWithStar(int day) {
        Assertions.assertThat(new StarDate(this.starDates).visitDateContainsStarDates(day)).isFalse();
    }
}
