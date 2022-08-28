package com.example.shopjava.entities.product;

import com.example.shopjava.entities.user.User;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private Date date = Date.from(Instant.now());

    @Column(nullable = false)
    private String review;

    @Column(nullable = false)
    private Boolean isRecommended;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Boolean getRecommended() {
        return isRecommended;
    }

    public void setRecommended(Boolean recommended) {
        isRecommended = recommended;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.user.addReview(this);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        this.product.addReview(this);
    }

    @Override
    public String toString() {
        return "Review{" +
                "rating=" + rating +
                ", date=" + date +
                ", review='" + review + '\'' +
                ", isRecommended=" + isRecommended +
                ", user=" + user +
                ", product=" + product +
                '}';
    }
}
