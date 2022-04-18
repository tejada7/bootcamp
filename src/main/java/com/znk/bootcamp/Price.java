package com.znk.bootcamp;

import java.math.BigDecimal;
import java.math.RoundingMode;

record Price(BigDecimal amount) {

    public static Price of(final double amount) {
        return new Price(BigDecimal.valueOf(amount));
    }

    public static Price of(final BigDecimal amount) {
        return new Price(amount);
    }

    public Price(final BigDecimal amount) {
        this.amount = amount.setScale(2, RoundingMode.UP);
    }

    Price times(final Quantity times) {
        return new Price(amount.multiply(BigDecimal.valueOf(times.quantity())));
    }

    Price applyDiscountRate() {
        return new DiscountRate(amount).resolvePriceWithDiscount();
    }

    Price applyTaxRateOf(final State state) {
        return new Tax(state, amount).resolvePriceTaxIncluded();
    }

    Price plus(final BigDecimal anotherAmount) {
        return new Price(amount.add(anotherAmount));
    }

    Price minus(final Price anotherAmount) {
        return new Price(amount.subtract(anotherAmount.amount));
    }

    @Override
    public String toString() {
        return amount.toString();
    }
}
