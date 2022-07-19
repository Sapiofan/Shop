package com.example.shopjava.repos;

import com.example.shopjava.entities.admin.AdminHome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<AdminHome, Long> {
}
