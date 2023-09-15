package com.aprakash.services;

import com.aprakash.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TransactionService {

    private final String bankSlogan;
    List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public TransactionService(@Value("${bank.slogan}") String bankSlogan) {
        this.bankSlogan = bankSlogan;
    }

    public List<Transaction> findAll() {
        return transactions;
    }

    public Transaction create(Integer amount, String transactionDetails) {
        ZonedDateTime timestamp = ZonedDateTime.now();
        Transaction transaction = new Transaction(amount, transactionDetails, timestamp, bankSlogan);
        transactions.add(transaction);
        return transaction;
    }
}
