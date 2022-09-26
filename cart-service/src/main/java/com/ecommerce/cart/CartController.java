package com.ecommerce.cart;

import com.ecommerce.cart.dto.CartRequest;
import com.ecommerce.cart.entity.Cart;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> addCartItem(@RequestBody CartRequest cartRequest) {
        return cartService.addCartItem(cartRequest);
    }

    @PutMapping
    public ResponseEntity<Cart> updateCartItem(@RequestBody CartRequest cartRequest) {
        return cartService.updateCartItem(cartRequest);
    }

    @GetMapping
    public ResponseEntity<Cart> getCartItem(
            @RequestParam(required = true, value = "customerId")
            @Parameter(description = "example: A177002") String id) {
        return cartService.getCartItem(id);
    }
}
