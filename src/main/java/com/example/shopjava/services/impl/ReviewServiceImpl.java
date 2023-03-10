package com.example.shopjava.services.impl;

import com.example.shopjava.entities.product.Product;
import com.example.shopjava.entities.product.Review;
import com.example.shopjava.entities.user.User;
import com.example.shopjava.repos.ProductRepo;
import com.example.shopjava.repos.ReviewRepo;
import com.example.shopjava.repos.UserRepo;
import com.example.shopjava.services.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepo reviewRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ProductRepo productRepository;

    private static final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Override
    @Transactional
    public Page<Review> getAll(int pageNum) {
        return reviewRepository.findAll(PageRequest.of(pageNum - 1, 5, Sort.by("date").descending()));
    }

    @Override
    @Transactional
    public boolean addReview(Integer rating, String text, Boolean isRecommended,
                             Authentication authentication, Long productId) {

        User user = userRepository.findByEmail(authentication.getName());
        List<Review> reviews = reviewRepository.findByUser(user.getId());

        for (Review review : reviews) {
            if (review.getProduct().getId().equals(productId)) {
                log.warn("Such review has already exist for this product: " + review.getId());
                return true;
            }
        }

        Review review = new Review();
        review.setReview(text);
        review.setDate(Date.from(Instant.now()));
        review.setProduct(productRepository.findProductById(productId));
        review.setRating(rating);
        review.setRecommended(isRecommended);
        review.setUser(user);
        reviewRepository.save(review);

        return false;
    }

    @Override
    @Transactional
    public List<Review> findReviewsByProduct(Long productId) {
        List<Review> reviews = reviewRepository.findReviewsByProduct(productId);
        reviews.sort(Comparator.comparingLong(o -> o.getDate().getTime()));

        return reviews;
    }

    @Override
    @Transactional
    public List<Review> findReviewsByUser(String email) {
        return reviewRepository.findByUserEmail(email);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Review review = reviewRepository.findReviewById(id);
        log.warn("Review was deleted: " + review);
        calculateRecommended(review.getProduct().getReviews());

        reviewRepository.deleteById(id);
    }

    @Override
    public Integer calculateRecommended(List<Review> reviews) {
        int count = 0;
        for (Review review : reviews) {
            if (review.getRecommended()) {
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }

        Product product = reviews.get(0).getProduct();
        float counter = 0;
        for (Review review : reviews) {
            counter += review.getRating();
        }

        product.setRating(0f);
        if(reviews.size() != 0) {
            product.setRating(counter / reviews.size());
        }
        productRepository.save(product);

        return count * 100 / reviews.size();
    }
}
