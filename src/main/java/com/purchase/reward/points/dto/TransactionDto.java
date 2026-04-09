package com.purchase.reward.points.dto;

import java.time.LocalDate;

// transaction data
public class TransactionDto {

    private Long customerId;
    private Double amount;
    private LocalDate transactionDate;

    public TransactionDto() {
    }

    public TransactionDto(Long customerId, Double amount, LocalDate transactionDate) {
        this.customerId = customerId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
