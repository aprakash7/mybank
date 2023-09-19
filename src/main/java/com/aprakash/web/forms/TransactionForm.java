package com.aprakash.web.forms;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class TransactionForm {

    @NotNull
    @Min(1)
    @Max(100)
    private Integer amount;

    @NotBlank
    @Size(min = 1, max = 25)
    private String transactionDetails;

    @NotBlank
    private String receivingUser;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public String getReceivingUser() {
        return receivingUser;
    }

    public void setReceivingUser(String receivingUser) {
        this.receivingUser = receivingUser;
    }
}