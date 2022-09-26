package com.ecommerce.customer.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private String errorMessage;
    private Date date;
    private String errorType;
}
