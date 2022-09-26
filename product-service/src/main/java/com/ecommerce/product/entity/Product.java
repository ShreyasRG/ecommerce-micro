package com.ecommerce.product.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

@Data
@Document(collection = "Product")
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

    @Transient
    public static final String SEQUENCE_NAME = "product_sequence";

    @Id
    private int id;
    private String productId;
    @Size(max = 100)
    @Indexed(unique = true)
    private String productName;
    private Float productPrice;
}
