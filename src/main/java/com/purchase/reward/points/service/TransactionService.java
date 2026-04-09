package com.purchase.reward.points.service;

import com.purchase.reward.points.dto.TransactionDto;
import com.purchase.reward.points.model.Transaction;

import java.util.List;

// service for transactions
public interface TransactionService {

    // get all transactions
    List<Transaction> getAllTransactions();

    // save a new transaction
    Transaction saveTransaction(TransactionDto transactionDto);

}
