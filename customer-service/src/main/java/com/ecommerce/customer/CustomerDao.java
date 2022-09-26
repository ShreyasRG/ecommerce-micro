package com.ecommerce.customer;

import com.ecommerce.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

    Boolean existsByCustomerEmail(String email);
    Boolean existsByCustomerPhone(Long phone);
    Boolean existsByCustomerId(String customerId);
    Customer findByCustomerId(String customerId);
}
