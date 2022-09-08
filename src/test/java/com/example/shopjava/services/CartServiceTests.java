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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTests {
    public static final long TEST_ID = 1l;
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
        cart.setId(TEST_ID);
        cart.setTotalPrice(10000);

        Product product = new Product();
        product.setId(TEST_ID);
        product.setName("product");
        product.setPrice(5000);

        User user = new User();
        user.setId(TEST_ID);

        when(cartRepository.save(cart)).thenReturn(cart);
        when(productRepository.findProductById(TEST_ID)).thenReturn(product);
        when(cartProductRepo.findCartProduct(TEST_ID, TEST_ID)).thenReturn(null);

        Cart cart1 = cartService.addProduct(cart, TEST_ID);

        verify(cartRepository, times(1)).save(cart);

        assertEquals(TEST_ID, cart1.getId());
        assertEquals(15000, cart1.getTotalPrice());
    }

    @Test
    public void getUserProductsTest() {

        User user = new User();
        user.setId(TEST_ID);
        user.setName("name");

        CartProduct product = new CartProduct();
        product.setId(TEST_ID);

        CartProduct product2 = new CartProduct();
        product2.setId(2l);

        Cart cart = new Cart();
        cart.setId(TEST_ID);
        cart.setUser(user);
        cart.setCartProducts(new HashSet<>(Arrays.asList(product, product2)));

        when(cartRepository.findProductsByUser(TEST_ID)).thenReturn(cart);

        Cart cart1 = cartService.getUserProducts(TEST_ID);

        verify(cartRepository, times(1)).findProductsByUser(TEST_ID);

        assertEquals("name", cart1.getUser().getName());
        assertEquals(TEST_ID, cart1.getId());
        assertEquals(2, cart1.getCartProducts().size());
    }

    @Test
    public void increaseProductTest() {
        Product product = new Product();
        product.setId(TEST_ID);
        product.setName("product");

        CartProduct cartProduct = new CartProduct();
        cartProduct.setId(TEST_ID);
        cartProduct.setTotal(10000);
        cartProduct.setProduct(product);
        cartProduct.setQuantity(1);

        Set<CartProduct> products = new HashSet<>();
        products.add(cartProduct);

        Cart cart = new Cart();
        cart.setId(TEST_ID);
        cart.setCartProducts(products);
        cart.setTotalPrice(10000);

        when(cartRepository.save(cart)).thenReturn(cart);

        when(cartProductRepo.save(cartProduct)).thenReturn(cartProduct);

        when(cartProductRepo.findCartProduct(TEST_ID, TEST_ID)).thenReturn(cartProduct);

        cartService.increaseQuantity(TEST_ID, cart);

        verify(cartProductRepo, times(1)).save(cartProduct);
        verify(cartRepository, times(1)).save(cart);
        verify(cartProductRepo, times(1)).findCartProduct(TEST_ID, TEST_ID);

    }

    @Test
    public void decreaseProductTest() {
        Product product = new Product();
        product.setId(TEST_ID);
        product.setName("product");

        CartProduct cartProduct = new CartProduct();
        cartProduct.setId(TEST_ID);
        cartProduct.setTotal(10000);
        cartProduct.setProduct(product);
        cartProduct.setQuantity(2);

        Set<CartProduct> products = new HashSet<>();
        products.add(cartProduct);

        Cart cart = new Cart();
        cart.setId(TEST_ID);
        cart.setCartProducts(products);
        cart.setTotalPrice(10000);

        when(cartRepository.save(cart)).thenReturn(cart);

        when(cartProductRepo.save(cartProduct)).thenReturn(cartProduct);

        when(cartProductRepo.findCartProduct(TEST_ID, TEST_ID)).thenReturn(cartProduct);

        cartService.decreaseQuantity(TEST_ID, cart);

        verify(cartProductRepo, times(1)).save(cartProduct);
        verify(cartRepository, times(1)).save(cart);
        verify(cartProductRepo, times(1)).findCartProduct(TEST_ID, TEST_ID);

    }

    @Test
    public void deleteProductFromCartTest() {
        Product product = new Product();
        product.setId(TEST_ID);
        product.setName("product");

        CartProduct cartProduct = new CartProduct();
        cartProduct.setId(TEST_ID);
        cartProduct.setTotal(10000);

        Set<CartProduct> products = new HashSet<>();
        products.add(cartProduct);

        Cart cart = new Cart();
        cart.setId(TEST_ID);
        cart.setCartProducts(products);
        cart.setTotalPrice(10000);

        when(cartRepository.save(cart)).thenReturn(cart);

        when(cartProductRepo.findCartProduct(TEST_ID, TEST_ID)).thenReturn(cartProduct);

        cartService.deleteProduct(cart, TEST_ID);

        verify(cartProductRepo, times(1)).delete(cartProduct);
    }
}
