package com.example.shopjava.repos;

import com.example.shopjava.entities.product.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WatchRepository extends JpaRepository<Watch, Long> {
}
