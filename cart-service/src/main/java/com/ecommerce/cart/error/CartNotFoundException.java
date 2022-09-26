package com.ecommerce.cart.error;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException() {

    }

    public CartNotFoundException(String message) {
        super(message);
    }
}
