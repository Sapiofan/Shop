package com.example.shopjava.repos;

import com.example.shopjava.entities.user.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
