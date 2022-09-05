package com.example.shopjava.repos;

import com.example.shopjava.entities.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category getCategoryByName(String name);
}
