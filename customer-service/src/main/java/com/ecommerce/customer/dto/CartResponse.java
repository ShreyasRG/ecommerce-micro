package com.ecommerce.customer.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartResponse {

    private int cartId;
    private String customerId;
    private List<Product> productList;
}
