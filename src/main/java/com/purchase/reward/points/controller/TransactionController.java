package com.purchase.reward.points.controller;

import com.purchase.reward.points.dto.TransactionDto;
import com.purchase.reward.points.model.Transaction;
import com.purchase.reward.points.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// rest controller for transactions
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // get all transactions
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    // add new transaction
    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody TransactionDto transactionDto) {
        Transaction saved = transactionService.saveTransaction(transactionDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
