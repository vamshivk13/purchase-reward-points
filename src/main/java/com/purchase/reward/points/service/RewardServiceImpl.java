package com.purchase.reward.points.service;

import com.purchase.reward.points.dto.CustomerRewardResponse;
import com.purchase.reward.points.model.Transaction;
import com.purchase.reward.points.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// implements reward logic
@Service
public class RewardServiceImpl implements RewardService {

    private final TransactionRepository transactionRepository;

    public RewardServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public CustomerRewardResponse getRewardsByCustomerId(Long customerId) {
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
        if (transactions == null || transactions.isEmpty()) {
            throw new com.purchase.reward.points.exception.CustomerNotFoundException(
                    "No transactions found for customer: " + customerId);
        }
        return calculateRewardForTransactions(customerId, transactions);
    }

    @Override
    public List<CustomerRewardResponse> getAllCustomerRewards() {
        List<Transaction> allTransactions = transactionRepository.findAll();
        Map<Long, List<Transaction>> transactionsPerCustomer = allTransactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerId));

        return transactionsPerCustomer.entrySet().stream()
                .map(entry -> calculateRewardForTransactions(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    // gets total and monthly points
    private CustomerRewardResponse calculateRewardForTransactions(Long customerId, List<Transaction> transactions) {
        Map<String, Long> pointsPerMonth = transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getTransactionDate().getMonth().toString(),
                        Collectors.summingLong(t -> calculatePoints(t.getAmount()))));

        long totalPoints = pointsPerMonth.values().stream().mapToLong(Long::longValue).sum();

        return new CustomerRewardResponse(customerId, totalPoints, pointsPerMonth);
    }

    public Long calculatePoints(Double amount) {
        if (amount == null || amount <= 50) {
            return 0L;
        }

        long points = 0;
        long truncatedAmount = amount.longValue();

        if (truncatedAmount > 100) {
            points += (truncatedAmount - 100) * 2;
            points += 50;
        } else if (truncatedAmount > 50) {
            points += (truncatedAmount - 50);
        }

        return points;
    }
}
