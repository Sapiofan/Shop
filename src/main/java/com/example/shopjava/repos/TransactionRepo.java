package com.example.shopjava.repos;

import com.example.shopjava.entities.user.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
}
