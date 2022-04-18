package com.znk.bootcamp;

import io.vavr.Function3;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final var quantity = scanner.nextInt();
        final var price = new BigDecimal(scanner.next());
        final var tax = State.valueOf(scanner.next());

        final var result = calculatePriceWithTax().curried()
                .apply(Quantity.of(quantity))
                .apply(Price.of(price))
                .apply(tax);

        System.out.println("Result: " + result);
    }

    public static Function3<Quantity, Price, State, Price> calculatePriceWithTax() {
        return (quantity, price, state) -> price.times(quantity)
                .applyDiscountRate()
                .applyTaxRateOf(state);
    }
}
