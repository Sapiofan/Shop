package com.example.shopjava.services.impl;

import com.example.shopjava.configs.security.CustomUserDetailsService;
import com.example.shopjava.entities.user.User;
import com.example.shopjava.entities.user.cart.Cart;
import com.example.shopjava.entities.user.cart.CartProduct;
import com.example.shopjava.entities.product.Product;
import com.example.shopjava.repos.CartProductRepo;
import com.example.shopjava.repos.CartRepo;
import com.example.shopjava.repos.ProductRepo;
import com.example.shopjava.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepository;

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private CartProductRepo cartProductRepo;

    @Autowired
    private CustomUserDetailsService userService;

    @Override
    @Transactional
    public Cart addProduct(Cart cart, Long productId) {
        Product product = productRepository.findProductById(productId);
        if (cartProductRepo.findCartProduct(productId, cart.getId()) == null) {
            cartProductRepo.save(new CartProduct(cart, product, 1, product.getPrice()));
            cart.setTotalPrice(cart.getTotalPrice() + product.getPrice());

            return cartRepository.save(cart);
        }

        return cart;
    }

    @Override
    @Transactional
    public void increaseQuantity(Long productId, Cart cart) {
        CartProduct cartProduct = cartProductRepo.findCartProduct(productId, cart.getId());
        Integer productPrice = cartProduct.getTotal() / cartProduct.getQuantity();
        cartProduct.setTotal(cartProduct.getTotal() + productPrice);
        cartProduct.setQuantity(cartProduct.getQuantity() + 1);
        cart.setTotalPrice(cart.getTotalPrice() + productPrice);
        cartRepository.save(cart);
        cartProductRepo.save(cartProduct);
    }

    @Override
    @Transactional
    public void decreaseQuantity(Long productId, Cart cart) {
        CartProduct cartProduct = cartProductRepo.findCartProduct(productId, cart.getId());
        if (cartProduct.getQuantity() > 1) {
            Integer productPrice = cartProduct.getTotal() / cartProduct.getQuantity();
            cartProduct.setTotal(cartProduct.getTotal() - productPrice);
            cartProduct.setQuantity(cartProduct.getQuantity() - 1);
            cart.setTotalPrice(cart.getTotalPrice() - productPrice);
            cartRepository.save(cart);
        }
        cartProductRepo.save(cartProduct);
    }

    @Override
    @Transactional
    public Cart getUserProducts(Long id) {
        return cartRepository.findProductsByUser(id);
    }

    @Override
    @Transactional
    public void deleteProduct(Cart cart, Long productId) {
        CartProduct cartProduct = cartProductRepo.findCartProduct(productId, cart.getId());
        cart.setTotalPrice(cart.getTotalPrice() - cartProduct.getTotal());
        cartRepository.save(cart);
        cartProductRepo.delete(cartProduct);
    }

    @Override
    public void cleanCart(Authentication authentication) {
        if(authentication == null) {
            return;
        }

        User user = userService.getUserByEmail(authentication.getName());
        user.setCart(new Cart(0, 0));
        userService.saveUser(user);
    }
}
