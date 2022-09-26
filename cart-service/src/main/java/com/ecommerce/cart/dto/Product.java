package com.ecommerce.cart.dto;

import lombok.Data;

@Data
public class Product {

    private String productId;
    private String productName;
    private Float productPrice;
    private Integer productQuantity;
}
