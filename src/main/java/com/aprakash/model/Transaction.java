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

    private String slogan;

    private String receivingUser;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mmZ")
    private ZonedDateTime timestamp;

    public Transaction() {
    }

    public Transaction(Integer amount, String transactionDetails, ZonedDateTime timestamp, String slogan, String receivingUser) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.transactionDetails = transactionDetails;
        this.timestamp = timestamp;
        this.slogan = slogan;
        this.receivingUser = receivingUser;
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

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReceivingUser() {
        return receivingUser;
    }

    public void setReceivingUser(String receivingUser) {
        this.receivingUser = receivingUser;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
}
