package com.ecommerce.cart;

import com.ecommerce.cart.dto.CartRequest;
import com.ecommerce.cart.entity.Cart;
import com.ecommerce.cart.error.CartNotFoundException;
import com.ecommerce.cart.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    public ResponseEntity<Cart> addCartItem(CartRequest cartRequest) {

        Cart cart = new Cart();
        BeanUtils.copyProperties(cartRequest, cart);
        Cart response = cartDao.save(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<Cart> getCartItem(String id) {

        Cart cart = cartDao.findByCustomerId(id);
        if (Objects.isNull(cart)) {
            throw new CartNotFoundException(Constants.CART_NOT_FOUND);
        }
        return ResponseEntity.ok(cart);
    }

    public ResponseEntity<Cart> updateCartItem(CartRequest cartRequest) {

        Cart cart = cartDao.findByCustomerId(cartRequest.getCustomerId());
        if (Objects.isNull(cart)) {
            throw new CartNotFoundException(Constants.CART_NOT_FOUND);
        }
        BeanUtils.copyProperties(cartRequest, cart);
        Cart response = cartDao.save(cart);

        return ResponseEntity.ok(response);
    }
}
