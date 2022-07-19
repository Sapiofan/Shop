package com.example.shopjava.services;

import com.example.shopjava.entities.product.Review;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ReviewService {
    Page<Review> getAll(int pageNum);

    boolean addReview(Integer rating, String text, Boolean isRecommended, Authentication authentication, Long productId);

    List<Review> findReviewsByProduct(Long productId);

    List<Review> findReviewsByUser(String email);

    void deleteById(Long id);

    Integer calculateRecommended(List<Review> reviews);
}
