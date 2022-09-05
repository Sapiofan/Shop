package com.example.shopjava.repos;

import com.example.shopjava.entities.product.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaptopRepo extends JpaRepository<Laptop, Long> {
    @Override
    List<Laptop> findAll();
}
