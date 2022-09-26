package com.ecommerce.cart;

import com.ecommerce.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDao extends JpaRepository<Cart, Integer> {

    Cart findByCustomerId(String id);
}
