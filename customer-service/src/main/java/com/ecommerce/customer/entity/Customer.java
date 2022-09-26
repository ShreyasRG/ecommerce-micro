package com.ecommerce.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String customerId;
    private String customerName;
    @Column(unique = true)
    private String customerEmail;
    @Column(unique = true)
    private Long customerPhone;
    private String customerAddress;
}
