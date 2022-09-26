package com.ecommerce.customer.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartRequest {

    private String customerId;
    private List<Product> productList;
}
