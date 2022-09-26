package com.ecommerce.product.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalException {

    ApiError error;

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> userNotFoundException(ProductNotFoundException exception) {

        error = new ApiError(exception.getLocalizedMessage(), new Date(), exception.getClass().getName());
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ApiError> customerException(ProductException exception) {

        return ResponseEntity.status(exception.status).body(exception.error);
    }
}
