package com.example.shopjava.services;

import com.example.shopjava.entities.product.Product;
import com.example.shopjava.entities.user.Favorite;
import com.example.shopjava.entities.user.User;
import com.example.shopjava.repos.FavoriteRepo;
import com.example.shopjava.repos.ProductRepo;
import com.example.shopjava.services.impl.FavoriteServiceImpl;
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
public class FavoriteServiceTest {
    @InjectMocks
    private FavoriteServiceImpl favoriteService;

    @Mock
    FavoriteRepo favoriteRepository;

    @Mock
    ProductRepo productRepository;

    @Test
    public void addFavoriteTest(){
        Favorite favorite = new Favorite();
        favorite.setId(1l);

        Product product = new Product();
        product.setId(1l);
        product.setName("product");

        when(favoriteRepository.save(favorite)).thenReturn(favorite);

        when(productRepository.findProductById(1l)).thenReturn(product);

        favoriteService.addProduct(favorite, 1l);

        verify(favoriteRepository).save(favorite);
    }

    @Test
    public void getUserProductsTest(){

        User user = new User();
        user.setId(1l);
        user.setName("name");

        Favorite favorite = new Favorite();
        favorite.setId(1l);
        favorite.setUser(user);

        when(favoriteRepository.findFavoriteByUser(1l)).thenReturn(favorite);

        Favorite favorite1 = favoriteService.getUserProducts(1l);

        verify(favoriteRepository).findFavoriteByUser(1l);

        Assertions.assertAll(() -> {
            assertEquals("name", favorite1.getUser().getName());
            assertEquals(1l, favorite1.getId());
        });
    }

    @Test
    public void deleteProductFromFavoriteTest(){
        Product product = new Product();
        product.setId(1l);
        product.setName("product");

        Set<Product> products = new HashSet<>();
        products.add(product);

        Favorite favorite = new Favorite();
        favorite.setId(1l);
        favorite.setFavoriteProducts(products);

        when(favoriteRepository.save(favorite)).thenReturn(favorite);

        Favorite favorite1 = favoriteService.deleteProduct(favorite, 1l);

        Assertions.assertAll(() -> {
            assertEquals(0, favorite1.getFavoriteProducts().size());
            assertEquals(1l, favorite1.getId());
        });

        verify(favoriteRepository).save(favorite);
    }

    @Test
    public void cleanWishlistTest(){
        Product product1 = new Product();
        product1.setId(1l);
        product1.setName("product");

        Product product2 = new Product();
        product2.setId(2l);
        product2.setName("product");

        Set<Product> products = new HashSet<>();
        products.add(product1);
        products.add(product2);

        Favorite favorite = new Favorite();
        favorite.setId(1l);
        favorite.setFavoriteProducts(products);

        when(favoriteRepository.save(favorite)).thenReturn(favorite);

        Favorite favorite1 = favoriteService.cleanFavorites(favorite);

        Assertions.assertAll(() -> {
            assertEquals(0, favorite1.getFavoriteProducts().size());
            assertEquals(1l, favorite1.getId());
        });

        verify(favoriteRepository).save(favorite);
    }
}
