package com.example.shopjava.controllers;

import com.example.shopjava.configs.security.CustomUserDetailsService;
import com.example.shopjava.entities.product.Product;
import com.example.shopjava.entities.user.User;
import com.example.shopjava.entities.user.cart.CartProduct;
import com.example.shopjava.services.CartService;
import com.example.shopjava.services.FilterProducts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class Commands {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private FilterProducts filterProducts;

    @Autowired
    private CartService cartService;

    private static final Logger log = LoggerFactory.getLogger(Commands.class);

    @GetMapping(value = "/cleanWishlist")
    @ResponseBody
    public Set<Product> cleanFavorites(Authentication authentication) {
        if (authentication != null) {
            User user = userDetailsService.getUserByEmail(authentication.getName());
            user.getFavorite().getFavoriteProducts().clear();
            userDetailsService.saveUser(user);

            return user.getFavorite().getFavoriteProducts();
        }

        return null;
    }

    @GetMapping(value = "/deleteFavorite/{id}")
    @ResponseBody
    public Set<Product> deleteFavorite(Authentication authentication, @PathVariable("id") Long productId) {
        if (authentication != null) {
            User user = userDetailsService.getUserByEmail(authentication.getName());
            user.getFavorite().getFavoriteProducts().remove(filterProducts.getProductById(productId));
            userDetailsService.saveUser(user);

            return user.getFavorite().getFavoriteProducts();
        }

        return null;
    }

    @GetMapping(value = "/deleteCartProduct/{id}")
    @ResponseBody
    public Set<CartProduct> deleteCartProduct(Authentication authentication, @PathVariable("id") Long productId) {
        if (authentication != null) {
            User user = userDetailsService.getUserByEmail(authentication.getName());
            cartService.deleteProduct(user.getCart(), productId);

            return user.getCart().getCartProducts();
        }

        return null;
    }

    @GetMapping(value = "/addToCart/{id}")
    @ResponseBody
    public Set<CartProduct> addToCart(Authentication authentication, @PathVariable("id") Long productId) {
        if (authentication != null) {
            User user = userDetailsService.getUserByEmail(authentication.getName());
            cartService.addProduct(user.getCart(), productId);

            return user.getCart().getCartProducts();
        }

        return null;
    }

    @GetMapping(value = "/addAdditionalProduct/{id}")
    @ResponseBody
    public Set<CartProduct> addAdditionalProduct(Authentication authentication, @PathVariable("id") Long productId) {
        if (authentication != null) {
            User user = userDetailsService.getUserByEmail(authentication.getName());
            cartService.increaseQuantity(productId, user.getCart());

            return user.getCart().getCartProducts();
        }

        return null;
    }

    @GetMapping(value = "/subtractAdditionalProduct/{id}")
    @ResponseBody
    public Set<CartProduct> subtractAdditionalProduct(Authentication authentication, @PathVariable("id") Long productId) {
        if (authentication != null) {
            User user = userDetailsService.getUserByEmail(authentication.getName());
            cartService.decreaseQuantity(productId, user.getCart());

            return user.getCart().getCartProducts();
        }

        return null;
    }

    @GetMapping(value = "/addProduct/{id}")
    @ResponseBody
    public Set<Product> addProductToFavorites(Authentication authentication, @PathVariable("id") Long productId) {
        if (authentication != null) {
            User user = userDetailsService.getUserByEmail(authentication.getName());
            user.getFavorite().getFavoriteProducts().add(filterProducts.getProductById(productId));
            userDetailsService.saveUser(user);

            return user.getFavorite().getFavoriteProducts();
        }

        return null;
    }
}
