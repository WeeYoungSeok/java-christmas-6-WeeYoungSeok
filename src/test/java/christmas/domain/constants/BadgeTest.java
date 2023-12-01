package christmas.domain.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BadgeTest {

    @ParameterizedTest
    @DisplayName("금액에 맞는 배지 부여")
    @MethodSource("badgeAndAmountSetting")
    void checkBadgeForAmount(Badge badge, int amount) {
        assertThat(badge.getWhichBadgeFromAmount(amount)).isTrue();
    }

    static Stream<Arguments> badgeAndAmountSetting() {
        return Stream.of(
                Arguments.arguments(Badge.NONE, 0),
                Arguments.arguments(Badge.NONE, 4000),
                Arguments.arguments(Badge.START, 5000),
                Arguments.arguments(Badge.START, 7000),
                Arguments.arguments(Badge.TREE, 10000),
                Arguments.arguments(Badge.TREE, 15000),
                Arguments.arguments(Badge.SANTA, 20000),
                Arguments.arguments(Badge.SANTA, 25000)
        );
    }
}
