package com.ecommerce.product.error;

import org.springframework.http.HttpStatus;

public class ProductException extends RuntimeException {
    ApiError error;
    HttpStatus status;

    public ProductException() {
    }

    public ProductException(String message) {
        super(message);
    }

    public ProductException(HttpStatus status, ApiError error) {
        this.error = error;
        this.status = status;
    }
}
