package com.example.shopjava.repos;


import com.example.shopjava.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p from Product p where lower(p.name) LIKE lower(concat('%', ?1,'%'))" +
            " or lower(p.brand) LIKE lower(concat('%', ?1,'%'))")
    List<Product> searchProducts(String keyword);

    @Query("select p from Product p where p.id=:id")
    Product findProductById(Long id);

    @Query("SELECT p from Product p where p.discount > 0")
    List<Product> discounts();

    @Query("delete from Product p where p.id=:id")
    @Modifying
    void deleteById(Long id);
}
