package com.example.shopjava.services.impl;

import com.example.shopjava.entities.user.Favorite;
import com.example.shopjava.entities.product.Product;
import com.example.shopjava.repos.FavoriteRepo;
import com.example.shopjava.repos.ProductRepo;
import com.example.shopjava.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepo favoriteRepository;

    @Autowired
    private ProductRepo productRepository;

    @Override
    @Transactional
    public Favorite addProduct(Favorite favorite, Long productId) {
        favorite.getFavoriteProducts().add(productRepository.findProductById(productId));
        favoriteRepository.save(favorite);
        return favorite;
    }

    @Override
    @Transactional
    public Favorite getUserProducts(Long id) {
        return favoriteRepository.findFavoriteByUser(id);
    }

    @Override
    @Transactional
    public Favorite deleteProduct(Favorite favorite, Long productId) {
        Set<Product> favorites = favorite.getFavoriteProducts();
        favorites.removeIf(product -> productId.equals(product.getId()));
        favoriteRepository.save(favorite);
        return favorite;
    }

    @Override
    @Transactional
    public Favorite cleanFavorites(Favorite favorite) {
        favorite.setFavoriteProducts(new HashSet<>());
        favoriteRepository.save(favorite);
        return favorite;
    }
}
