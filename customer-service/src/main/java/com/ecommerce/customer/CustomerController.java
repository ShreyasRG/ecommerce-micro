package com.ecommerce.customer;

import com.ecommerce.customer.dto.CartResponse;
import com.ecommerce.customer.dto.CustomerRequest;
import com.ecommerce.customer.entity.Customer;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerRequest request) {

        return customerService.addCustomer(request);
    }

    @GetMapping
    public ResponseEntity<Customer> findCustomerById(
            @Parameter(description = "example: A177002")
            @RequestParam(required = true, value = "customerId") String id) {

        return customerService.findCustomerById(id);
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(
            @RequestParam(required = true, value = "customerId")
            @Parameter(description = "example: A177002") String id,
            @RequestBody CustomerRequest request) {

        return customerService.updateCustomer(request, id);
    }

    @PostMapping("/products/carts")
    public ResponseEntity<CartResponse> addProductToCart(
            @RequestParam(required = true, value = "productId")
            @Parameter(description = "example: P01") String productId,
            @RequestParam(required = true, value = "quantity")
            @Parameter(description = "example: 1") int quantity,
            @RequestParam(required = true, value = "customerId")
            @Parameter(description = "example: A177002") String customerId) {

        return customerService.addProductToCart(productId, quantity, customerId);
    }

    @GetMapping("/carts")
    public ResponseEntity<CartResponse> getCustomerCart(
            @RequestParam(required = true, value = "customerId")
            @Parameter(description = "example: A177002") String id) {

        return customerService.getCustomerCart(id);
    }
}
