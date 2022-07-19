package com.example.shopjava.services;

import com.example.shopjava.entities.user.Favorite;

public interface FavoriteService {
    Favorite addProduct(Favorite favorite, Long productId);

    Favorite getUserProducts(Long id);

    Favorite deleteProduct(Favorite favorite, Long productId);

    Favorite cleanFavorites(Favorite favorite);
}
