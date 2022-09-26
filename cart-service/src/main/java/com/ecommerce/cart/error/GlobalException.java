package com.ecommerce.cart.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ApiError> CartNotFoundException(CartNotFoundException exception) {

        ApiError error = new ApiError(exception.getLocalizedMessage(), new Date(), exception.getClass().getName());
        return ResponseEntity.badRequest().body(error);
    }
}
