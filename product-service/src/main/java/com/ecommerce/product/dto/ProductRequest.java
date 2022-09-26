package com.ecommerce.product.dto;

import lombok.Data;

@Data
public class ProductRequest {

    private int id;
    private String productId;
    private String productName;
    private float productPrice;
}
