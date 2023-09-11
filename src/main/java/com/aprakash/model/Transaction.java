package com.aprakash.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Transaction {

    @JsonProperty("transaction_id")
    private String id;

    @JsonProperty("transaction_details")
    private String transactionDetails;
    private Integer amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mmZ")
    private ZonedDateTime timestamp;

    public Transaction() {
    }

    public Transaction(Integer amount, String transactionDetails, ZonedDateTime timestamp) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.transactionDetails = transactionDetails;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails() {
        this.transactionDetails = transactionDetails;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount() {
        this.amount = amount;
    }
}
