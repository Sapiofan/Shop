package com.example.shopjava.services;

import com.example.shopjava.entities.user.cart.Cart;
import org.springframework.security.core.Authentication;

public interface CartService {
    Cart addProduct(Cart cart, Long productId);

    Cart getUserProducts(Long id);

    void increaseQuantity(Long productId, Cart cart);

    void decreaseQuantity(Long productId, Cart cart);

    void deleteProduct(Cart cart, Long productId);

    void cleanCart(Authentication authentication);
}
