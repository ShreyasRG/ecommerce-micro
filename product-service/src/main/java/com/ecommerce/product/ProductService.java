package com.ecommerce.product;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.error.ProductNotFoundException;
import com.ecommerce.product.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public ResponseEntity<Product> addProduct(ProductRequest productRequest) {

        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        Product response = productDao.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<Product> findByProductId(String id) {

        Product product = productDao.findByProductId(id);
        return ResponseEntity.ok(product);
    }
}
