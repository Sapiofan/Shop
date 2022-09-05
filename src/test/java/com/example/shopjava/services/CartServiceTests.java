package com.example.shopjava.services;

import com.example.shopjava.entities.product.Product;
import com.example.shopjava.entities.user.User;
import com.example.shopjava.entities.user.cart.Cart;
import com.example.shopjava.entities.user.cart.CartProduct;
import com.example.shopjava.repos.CartProductRepo;
import com.example.shopjava.repos.CartRepo;
import com.example.shopjava.repos.ProductRepo;
import com.example.shopjava.services.impl.CartServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceTests {
    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    CartRepo cartRepository;

    @Mock
    ProductRepo productRepository;

    @Mock
    CartProductRepo cartProductRepo;

    @Test
    public void addToCart() {
        Cart cart = new Cart();
        cart.setId(1l);
        cart.setTotalPrice(10000);

        Product product = new Product();
        product.setId(1l);
        product.setName("product");
        product.setPrice(5000);

        User user = new User();
        user.setId(1l);

        when(cartRepository.save(cart)).thenReturn(cart);

        when(productRepository.findProductById(1l)).thenReturn(product);

        when(cartProductRepo.findCartProduct(1l, 1l)).thenReturn(null);

        Cart cart1 = cartService.addProduct(cart, 1l);

        verify(cartRepository).save(cart);

        Assertions.assertAll(() -> {
            assertEquals(1l, cart1.getId());
            assertEquals(15000, cart1.getTotalPrice());
        });
    }

    @Test
    public void getUserProductsTest() {

        User user = new User();
        user.setId(1l);
        user.setName("name");

        Cart cart = new Cart();
        cart.setId(1l);
        cart.setUser(user);

        when(cartRepository.findProductsByUser(1l)).thenReturn(cart);

        Cart cart1 = cartService.getUserProducts(1l);

        verify(cartRepository).findProductsByUser(1l);

        Assertions.assertAll(() -> {
            assertEquals("name", cart1.getUser().getName());
            assertEquals(1l, cart1.getId());
        });
    }

    @Test
    public void increaseProductTest() {
        Product product = new Product();
        product.setId(1l);
        product.setName("product");

        CartProduct cartProduct = new CartProduct();
        cartProduct.setId(1l);
        cartProduct.setTotal(10000);
        cartProduct.setProduct(product);
        cartProduct.setQuantity(1);

        Set<CartProduct> products = new HashSet<>();
        products.add(cartProduct);

        Cart cart = new Cart();
        cart.setId(1l);
        cart.setCartProducts(products);
        cart.setTotalPrice(10000);

        when(cartRepository.save(cart)).thenReturn(cart);

        when(cartProductRepo.save(cartProduct)).thenReturn(cartProduct);

        when(cartProductRepo.findCartProduct(1l, 1l)).thenReturn(cartProduct);

        cartService.increaseQuantity(1l, cart);

        verify(cartProductRepo).save(cartProduct);
        verify(cartRepository).save(cart);
        verify(cartProductRepo).findCartProduct(1l, 1l);

    }

    @Test
    public void decreaseProductTest() {
        Product product = new Product();
        product.setId(1l);
        product.setName("product");

        CartProduct cartProduct = new CartProduct();
        cartProduct.setId(1l);
        cartProduct.setTotal(10000);
        cartProduct.setProduct(product);
        cartProduct.setQuantity(2);

        Set<CartProduct> products = new HashSet<>();
        products.add(cartProduct);

        Cart cart = new Cart();
        cart.setId(1l);
        cart.setCartProducts(products);
        cart.setTotalPrice(10000);

        when(cartRepository.save(cart)).thenReturn(cart);

        when(cartProductRepo.save(cartProduct)).thenReturn(cartProduct);

        when(cartProductRepo.findCartProduct(1l, 1l)).thenReturn(cartProduct);

        cartService.decreaseQuantity(1l, cart);

        verify(cartProductRepo).save(cartProduct);
        verify(cartRepository).save(cart);
        verify(cartProductRepo).findCartProduct(1l, 1l);

    }

    @Test
    public void deleteProductFromCartTest() {
        Product product = new Product();
        product.setId(1l);
        product.setName("product");

        CartProduct cartProduct = new CartProduct();
        cartProduct.setId(1l);
        cartProduct.setTotal(10000);

        Set<CartProduct> products = new HashSet<>();
        products.add(cartProduct);

        Cart cart = new Cart();
        cart.setId(1l);
        cart.setCartProducts(products);
        cart.setTotalPrice(10000);

        when(cartRepository.save(cart)).thenReturn(cart);

        when(cartProductRepo.findCartProduct(1l, 1l)).thenReturn(cartProduct);

        cartService.deleteProduct(cart, 1l);

        verify(cartProductRepo).delete(cartProduct);
    }
}
