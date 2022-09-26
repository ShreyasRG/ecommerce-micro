package com.ecommerce.product.error;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {

    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
