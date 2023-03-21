package com.example.shopjava.entities.product;

import com.example.shopjava.entities.user.Favorite;
import com.example.shopjava.entities.user.cart.CartProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String payment;

    @Column(nullable = false)
    private Float rating;

    @Column
    private Integer discount;

    @Column
    private String gifts;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Column(nullable = false)
    private Integer warranty;

    @Column
    private Date addedAt;

    @Column
    private Integer sold;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "product")
    private CartProduct cartProduct;

    @JsonIgnore
    @ManyToMany(mappedBy = "favoriteProducts", fetch = FetchType.EAGER)
    private Set<Favorite> favorites = new HashSet<>();

    public Product() {
    }

    public Product(String image, String name, Integer price, String brand, String payment, Float rating,
                   Integer discount, String gifts, Boolean isAvailable, Integer warranty, Date addedAt,
                   Integer sold, Category category) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.payment = payment;
        this.rating = rating;
        this.discount = discount;
        this.gifts = gifts;
        this.isAvailable = isAvailable;
        this.warranty = warranty;
        this.addedAt = addedAt;
        this.sold = sold;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getGifts() {
        return gifts;
    }

    public void setGifts(String gifts) {
        this.gifts = gifts;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Integer getWarranty() {
        return warranty;
    }

    public void setWarranty(Integer warranty) {
        this.warranty = warranty;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        this.category.addProduct(this);
    }

    public CartProduct getCartProduct() {
        return cartProduct;
    }

    public void setCartProduct(CartProduct cartProduct) {
        this.cartProduct = cartProduct;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(brand, product.brand) && Objects.equals(payment, product.payment) && Objects.equals(rating, product.rating) && Objects.equals(discount, product.discount) && Objects.equals(gifts, product.gifts) && Objects.equals(isAvailable, product.isAvailable) && Objects.equals(warranty, product.warranty) && Objects.equals(category, product.category) && Objects.equals(reviews, product.reviews) && Objects.equals(favorites, product.favorites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, brand, payment, rating, discount, gifts, isAvailable, warranty, category, reviews, favorites);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}
