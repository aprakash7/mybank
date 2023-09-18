package com.aprakash.web;

import com.aprakash.model.Transaction;
import com.aprakash.dto.TransactionDto;
import com.aprakash.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping("/transactions")
    public List<Transaction> transactions() {
        return transactionService.findAll();
    }

    @PostMapping("/transactions")
    public Transaction create(@RequestBody @Valid TransactionDto transactionDto) {
        return transactionService.create(transactionDto.getAmount(), transactionDto.getTransactionDetails());
    }

}
