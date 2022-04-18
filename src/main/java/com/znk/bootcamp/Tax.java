package com.znk.bootcamp;

import java.math.BigDecimal;

record Tax(State state, BigDecimal amount) {

    Price resolvePriceTaxIncluded() {
        return switch (state) {
            case AL -> new Price(amount).plus(ofPercentage(4));
            case CA -> new Price(amount).plus(ofPercentage(8.25));
            case TX -> new Price(amount).plus(ofPercentage(6.25));
            case UT -> new Price(amount).plus(ofPercentage(6.85));
            case NV -> new Price(amount).plus(ofPercentage(8));
        };
    }

    private BigDecimal ofPercentage(final double percentage) {
        return amount.multiply(BigDecimal.valueOf(percentage / 100));
    }
}
