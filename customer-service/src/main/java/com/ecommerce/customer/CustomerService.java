package com.ecommerce.customer;

import com.ecommerce.customer.dto.*;
import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.error.CustomerException;
import com.ecommerce.customer.error.UserNotFoundException;
import com.ecommerce.customer.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${productGetUrl}")
    private String productGetUrl;

    @Value("${cartAddUrl}")
    private String cartAddUrl;

    @Value("${cartGetUrl}")
    private String cartGetUrl;

    @Value("${cartUpdateUrl}")
    private String cartUpdateUrl;

    public ResponseEntity<Customer> addCustomer(CustomerRequest request) {

        if (customerDao.existsByCustomerEmail(request.getCustomerEmail())
                || customerDao.existsByCustomerPhone(request.getCustomerPhone())
                || customerDao.existsByCustomerId(request.getCustomerId())) {
            throw new CustomerException(Constants.USERALREADYEXISTS);
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(request, customer);
        customer = customerDao.save(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    public ResponseEntity<Customer> findCustomerById(String id) {

        Customer customer = customerDao.findByCustomerId(id);
        if (Objects.isNull(customer)) {
            throw new UserNotFoundException(Constants.USERNOTFOUND);
        }
        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<Customer> updateCustomer(CustomerRequest request, String id) {

        Customer customer = customerDao.findByCustomerId(id);
        if (Objects.isNull(customer)) {
            throw new UserNotFoundException(Constants.USERNOTFOUND);
        }
        BeanUtils.copyProperties(request, customer);
        customer = customerDao.save(customer);

        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<CartResponse> addProductToCart(String productId, int quantity, String customerId) {

        CartRequest cartRequest = new CartRequest();
        ResponseEntity<CartResponse> cartResponse;
        HttpEntity httpEntity;

        // To check customer
        Customer customer = customerDao.findByCustomerId(customerId);
        if (customer == null) {
            throw new UserNotFoundException(Constants.USERNOTFOUND);
        }

        // To get product
        Product product = restTemplate.getForObject(productGetUrl.concat(productId), Product.class);
        product.setProductQuantity(quantity);

        cartRequest.setCustomerId(customerId);

        CartResponse cart = restTemplate.getForObject(cartGetUrl.concat(customerId), CartResponse.class);
        if (cart == null) {
            cartRequest.setProductList(Arrays.asList(product));
            httpEntity = new HttpEntity<>(cartRequest);
            cartResponse = restTemplate.exchange(cartAddUrl, HttpMethod.POST, httpEntity, CartResponse.class);
        } else {
            List<Product> productList = cart.getProductList();
            productList.add(product);
            cartRequest.setProductList(productList);
            httpEntity = new HttpEntity(cartRequest);
            cartResponse = restTemplate.exchange(cartUpdateUrl, HttpMethod.PUT, httpEntity, CartResponse.class);
        }
        return cartResponse;
    }

    public ResponseEntity<CartResponse> getCustomerCart(String id) {

        CartResponse cartResponse = restTemplate.getForObject(cartGetUrl.concat(id), CartResponse.class);
        if (Objects.isNull(cartResponse)) {
            return ResponseEntity.ok(new CartResponse());
        }
        return ResponseEntity.ok(cartResponse);
    }
}
