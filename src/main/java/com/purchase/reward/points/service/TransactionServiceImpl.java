package com.purchase.reward.points.service;

import com.purchase.reward.points.dto.TransactionDto;
import com.purchase.reward.points.model.Transaction;
import com.purchase.reward.points.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// implements transaction logic
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // gets all transactions
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction saveTransaction(TransactionDto dto) {
        Transaction transaction = new Transaction();
        transaction.setCustomerId(dto.getCustomerId());
        transaction.setAmount(dto.getAmount());
        transaction.setTransactionDate(dto.getTransactionDate());

        return transactionRepository.save(transaction);
    }
}
