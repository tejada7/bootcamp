package com.znk.bootcamp;

import java.math.BigDecimal;
import java.util.function.Predicate;

import static com.znk.bootcamp.Price.of;
import static io.vavr.API.*;

record DiscountRate(BigDecimal amount) {
    Price resolvePriceWithDiscount() {
        return Match(amount).of(
                Case($(isBetween(1000, 5000)), () -> amountMinusDiscountOfRate(0.03)),
                Case($(isBetween(5000, 7000)), () -> amountMinusDiscountOfRate(0.05)),
                Case($(isBetween(7000, 10000)), () -> amountMinusDiscountOfRate(0.07)),
                Case($(isBetween(10000, 50000)), () -> amountMinusDiscountOfRate(0.1)),
                Case($(isGreaterThan(50000)), () -> amountMinusDiscountOfRate(0.15)),
                Case($(), () -> of(amount))
        );
    }

    private Predicate<BigDecimal> isGreaterThan(final int from) {
        return amount -> amount.compareTo(BigDecimal.valueOf(from)) >= 0;
    }

    private Price amountMinusDiscountOfRate(final double rate) {
        return new Price(amount.subtract(amount.multiply(BigDecimal.valueOf(rate))));
    }

    private Predicate<BigDecimal> isBetween(final int from, final int to) {
        return amount -> amount.compareTo(BigDecimal.valueOf(from)) >= 0
                && amount.compareTo(BigDecimal.valueOf(to)) < 0;
    }
}
