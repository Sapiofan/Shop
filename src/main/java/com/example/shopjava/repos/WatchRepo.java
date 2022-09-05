package com.example.shopjava.repos;

import com.example.shopjava.entities.product.Watch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchRepo extends JpaRepository<Watch, Long> {
}
