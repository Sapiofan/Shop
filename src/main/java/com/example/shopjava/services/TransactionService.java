package com.example.shopjava.services;

import com.example.shopjava.entities.user.Transaction;

public interface TransactionService {
    Transaction addNewTransaction(String name, String phone, String email, String city, String card, String date, String cvv, Integer total);
}
