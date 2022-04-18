package com.znk.bootcamp;

record Quantity(int quantity) {
    public static Quantity of(int quantity) {
        return new Quantity(quantity);
    }
}
