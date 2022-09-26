package com.ecommerce.customer.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalException {

    ApiError error;

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> userNotFoundException(UserNotFoundException exception) {

        error = new ApiError(exception.getLocalizedMessage(), new Date(), exception.getClass().getName());
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ApiError> customerException(CustomerException exception) {

        error = new ApiError(exception.getLocalizedMessage(), new Date(), exception.getClass().getName());
        return ResponseEntity.badRequest().body(error);
    }
}
