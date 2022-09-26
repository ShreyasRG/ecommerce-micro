package com.ecommerce.customer.dto;

import lombok.Data;

@Data
public class CustomerRequest {

    private String customerId;
    private String customerName;
    private String customerEmail;
    private Long customerPhone;
    private String customerAddress;
}
