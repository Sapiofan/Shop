package com.example.shopjava.services;

import com.example.shopjava.entities.another.Career;
import com.example.shopjava.entities.product.Product;
import com.example.shopjava.entities.product.Review;
import com.example.shopjava.repos.ProductRepository;
import com.example.shopjava.repos.ReviewRepository;
import com.example.shopjava.repos.TransactionRepository;
import com.example.shopjava.services.impl.ReviewServiceImpl;
import com.example.shopjava.services.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTests {

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Mock
    ReviewRepository reviewRepository;

    @Mock
    ProductRepository productRepository;

    @Test
    public void getReviewsTest(){
        Review review1 = new Review();
        review1.setReview("review");
        review1.setId(1l);
        review1.setDate(Date.from(Instant.now()));

        Review review2 = new Review();
        review2.setReview("review 2");
        review2.setId(2l);
        review2.setDate(Date.from(Instant.now()));

        List<Review> reviews = new ArrayList<>();
        reviews.add(review1);
        reviews.add(review2);

        Page<Review> reviewPage = new PageImpl<>(reviews);

        Pageable pageable = PageRequest.of(0, 5, Sort.by("date").descending());

        when(reviewRepository.findAll(pageable)).thenReturn(reviewPage);

        Page<Review> reviewPage1 = reviewService.getAll(1);

        Assertions.assertEquals(2, reviewPage1.getTotalElements());
        Assertions.assertEquals(1, reviewPage1.getTotalPages());
    }

    @Test
    public void findReviewsByProductTest(){
        Product product1 = new Product();
        product1.setId(1l);

        Review review1 = new Review();
        review1.setReview("review");
        review1.setId(1l);
        review1.setProduct(product1);

        Review review2 = new Review();
        review2.setReview("review 2");
        review2.setId(2l);
        review2.setProduct(product1);

        List<Review> reviews = new ArrayList<>();
        reviews.add(review1);
        reviews.add(review2);

        when(reviewRepository.findReviewsByProduct(1l)).thenReturn(reviews);

        List<Review> reviews1 = reviewService.findReviewsByProduct(1l);

        Assertions.assertEquals(2, reviews1.size());
        Assertions.assertEquals("review", reviews1.get(0).getReview());
    }

    @Test
    public void deleteReviewById(){
        Review review1 = new Review();
        review1.setReview("review");
        review1.setId(1l);

        when(reviewRepository.findReviewById(1l)).thenReturn(review1);

        reviewService.deleteById(1l);
        verify(reviewRepository).deleteById(1l);
    }

    @Test
    public void calculateRecommended(){
        Product product1 = new Product();
        product1.setId(1l);
        product1.setRating(4.0f);

        Review review1 = new Review();
        review1.setReview("review");
        review1.setId(1l);
        review1.setProduct(product1);
        review1.setRating(5);
        review1.setRecommended(true);

        Review review2 = new Review();
        review2.setReview("review 2");
        review2.setId(2l);
        review2.setProduct(product1);
        review2.setRating(3);
        review2.setRecommended(false);

        List<Review> reviews = new ArrayList<>();
        reviews.add(review1);
        reviews.add(review2);

        when(productRepository.save(product1)).thenReturn(product1);

        int res = reviewService.calculateRecommended(reviews);

        Assertions.assertEquals(50, res);
    }
}
