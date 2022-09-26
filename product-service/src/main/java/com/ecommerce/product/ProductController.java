package com.ecommerce.product;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.entity.SequenceGeneratorService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductRequest productRequest) {
        productRequest.setId(sequenceGeneratorService.generateSequence(Product.SEQUENCE_NAME));
        return service.addProduct(productRequest);
    }

    @GetMapping
    public ResponseEntity<Product> findProductById(
            @Parameter(description = "example : P01")
            @RequestParam(required = true, value = "productId") String id) {

        return service.findByProductId(id);
    }
}
