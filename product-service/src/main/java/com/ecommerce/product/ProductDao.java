package com.ecommerce.product;

import com.ecommerce.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends MongoRepository<Product, Integer> {

    Product findByProductId(String id);
}
