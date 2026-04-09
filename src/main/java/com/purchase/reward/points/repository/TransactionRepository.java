package com.purchase.reward.points.repository;

import com.purchase.reward.points.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// repo for tx
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // find by customer
    List<Transaction> findByCustomerId(Long customerId);
}
