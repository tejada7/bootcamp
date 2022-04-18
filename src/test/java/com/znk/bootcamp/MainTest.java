package com.znk.bootcamp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.assertThat;

class MainTest {

    private Main main = new Main();

    @ParameterizedTest
    @MethodSource("acceptanceData")
    void acceptance_test(final Quantity quantity, final Price price, final State state, final Price expected) {
        assertThat(main.calculatePriceWithTax().curried()
                           .apply(quantity)
                           .apply(price)
                           .apply(state))
                .isEqualTo(expected);
    }

    public static Stream<Arguments> acceptanceData() {
        return Stream.of(
                Arguments.of(Quantity.of(1), Price.of(300), State.CA, Price.of(324.75)),
                Arguments.of(Quantity.of(3), Price.of(17), State.CA, Price.of(55.21)),
                Arguments.of(Quantity.of(5), Price.of(23), State.TX, Price.of(122.19)),
                Arguments.of(Quantity.of(22), Price.of(2345), State.TX, Price.of(46592.22)),
                Arguments.of(Quantity.of(3), Price.of(334), State.TX, Price.of(1032.69)),
                Arguments.of(Quantity.of(50), Price.of(21), State.NV, Price.of(1099.98)),
                Arguments.of(Quantity.of(44), Price.of(1295), State.UT, Price.of(51750.67)),
                Arguments.of(Quantity.of(9), Price.of(2315), State.UT, Price.of(20035.98)),
                Arguments.of(Quantity.of(27), Price.of(362), State.AL, Price.of(9453.42))
        );
    }
}
