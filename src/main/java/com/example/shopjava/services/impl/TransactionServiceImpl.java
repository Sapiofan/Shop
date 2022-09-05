package com.example.shopjava.services.impl;

import com.example.shopjava.entities.user.Transaction;
import com.example.shopjava.repos.TransactionRepo;
import com.example.shopjava.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionRepo transactionRepository;

    @Override
    @Transactional
    public Transaction addNewTransaction(String name, String phone, String email, String city,
                                         String card, String date, String cvv, Integer total) {

        Transaction transaction = new Transaction(total, "Privat24", email, phone, city, name);
        transactionRepository.save(transaction);
        log.info("Transaction was successful: " + transaction.getId());

        return transaction;
    }
}
