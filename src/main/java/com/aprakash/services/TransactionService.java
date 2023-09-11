package com.aprakash.services;

import com.aprakash.model.Transaction;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TransactionService {
    List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public TransactionService() {
    }

    public List<Transaction> findAll() {
        return transactions;
    }

    public Transaction create(Integer amount, String transactionDetails) {
        ZonedDateTime timestamp = ZonedDateTime.now();
        Transaction transaction = new Transaction(amount, transactionDetails, timestamp);
        transactions.add(transaction);
        return transaction;
    }
}
