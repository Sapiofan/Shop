package com.example.shopjava.services;

import com.example.shopjava.entities.user.Transaction;
import com.example.shopjava.repos.TransactionRepository;
import com.example.shopjava.services.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    TransactionRepository repository;

    @Test
    public void addTransactionTest(){

        Transaction transaction1 = transactionService.addNewTransaction("name", "phone", "email", "city",
                "card", "date", "cvv", 10000);

        Assertions.assertEquals(transaction1.getAmount(), 10000);
        Assertions.assertEquals(transaction1.getName(), "name");
        Assertions.assertEquals(transaction1.getEmail(), "email");
    }
}
