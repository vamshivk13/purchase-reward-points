package com.purchase.reward.points.service;

import com.purchase.reward.points.model.Transaction;
import com.purchase.reward.points.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

// setup some mock data
@Service
public class MockDataService {

    private static final Logger logger = LoggerFactory.getLogger(MockDataService.class);

    private final TransactionRepository transactionRepository;

    @Value("${app.mock.enabled:false}")
    private boolean mockEnabled;

    public MockDataService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @PostConstruct
    public void init() {
        if (mockEnabled) {
            logger.info("Mock data generation is enabled. Populating transactions...");
            try {
                populateMockData();
            } catch (Exception e) {
                logger.error("Failed to populate mock data. Database might be unavailable. Processing will continue.",
                        e);
            }
        } else {
            logger.info("Mock data generation is disabled.");
        }
    }

    private void populateMockData() {
        // Checking if data already exists
        if (transactionRepository.count() > 0) {
            logger.info("Database already contains data. Skipping mock data generation.");
            return;
        }

        LocalDate now = LocalDate.now();

        // Customer 1: Has transactions in three different months
        Transaction t1 = new Transaction(1L, 120.0, now.minusMonths(2).withDayOfMonth(15)); // Expected points = 90
        Transaction t2 = new Transaction(1L, 50.0, now.minusMonths(1).withDayOfMonth(10)); // Expected points = 0
        Transaction t3 = new Transaction(1L, 75.0, now.minusMonths(1).withDayOfMonth(25)); // Expected points = 25
        Transaction t4 = new Transaction(1L, 200.0, now.minusDays(5)); // Expected points = 250

        // Customer 2: Moderate spender
        Transaction t5 = new Transaction(2L, 80.0, now.minusMonths(2).withDayOfMonth(5)); // Expected points = 30
        Transaction t6 = new Transaction(2L, 100.0, now.minusMonths(1).withDayOfMonth(12)); // Expected points = 50

        // Customer 3: No rewards
        Transaction t7 = new Transaction(3L, 20.0, now.minusDays(10)); // Expected points = 0

        List<Transaction> mockTransactions = Arrays.asList(t1, t2, t3, t4, t5, t6, t7);
        transactionRepository.saveAll(mockTransactions);

        logger.info("Successfully populated {} mock transactions.", mockTransactions.size());
    }
}
