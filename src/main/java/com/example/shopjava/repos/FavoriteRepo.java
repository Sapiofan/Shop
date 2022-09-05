package com.example.shopjava.repos;

import com.example.shopjava.entities.user.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FavoriteRepo extends JpaRepository<Favorite, Long> {
    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId")
    Favorite findFavoriteByUser(Long userId);

}
