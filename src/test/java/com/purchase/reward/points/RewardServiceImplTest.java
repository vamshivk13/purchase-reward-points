package com.purchase.reward.points;

import com.purchase.reward.points.dto.CustomerRewardResponse;
import com.purchase.reward.points.exception.CustomerNotFoundException;
import com.purchase.reward.points.model.Transaction;
import com.purchase.reward.points.repository.TransactionRepository;
import com.purchase.reward.points.service.RewardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RewardServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    private RewardServiceImpl rewardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rewardService = new RewardServiceImpl(transactionRepository);
    }

    @Test
    void testCalculatePoints_below50() {
        assertEquals(0L, rewardService.calculatePoints(40.0));
    }

    @Test
    void testCalculatePoints_between50And100() {
        assertEquals(20L, rewardService.calculatePoints(70.0)); // 70 - 50 = 20
    }

    @Test
    void testCalculatePoints_above100() {
        assertEquals(90L, rewardService.calculatePoints(120.0)); // (120 - 100) * 2 + 50 = 90
    }

    @Test
    void testGetRewardsByCustomerId_success() {
        LocalDate currentMonthRef = LocalDate.now();
        List<Transaction> mockTransactions = Arrays.asList(
                new Transaction(1L, 120.0, currentMonthRef), // 90 points
                new Transaction(1L, 120.0, currentMonthRef)  // 90 points
        );

        when(transactionRepository.findByCustomerId(1L)).thenReturn(mockTransactions);

        CustomerRewardResponse response = rewardService.getRewardsByCustomerId(1L);

        assertEquals(1L, response.getCustomerId());
        assertEquals(180L, response.getTotalPoints());
        assertEquals(180L, response.getPointsPerMonth().get(currentMonthRef.getMonth().toString()));
    }

    @Test
    void testGetRewardsByCustomerId_notFound() {
        when(transactionRepository.findByCustomerId(2L)).thenReturn(Collections.emptyList());

        assertThrows(CustomerNotFoundException.class, () -> rewardService.getRewardsByCustomerId(2L));
    }

    @Test
    void testGetAllCustomerRewards() {
        LocalDate currentMonthRef = LocalDate.now();
        List<Transaction> mockTransactions = Arrays.asList(
                new Transaction(1L, 120.0, currentMonthRef), // Customer 1: 90 points
                new Transaction(2L, 70.0, currentMonthRef)   // Customer 2: 20 points
        );

        when(transactionRepository.findAll()).thenReturn(mockTransactions);

        List<CustomerRewardResponse> responses = rewardService.getAllCustomerRewards();

        assertEquals(2, responses.size());
        
        CustomerRewardResponse c1Response = responses.stream().filter(r -> r.getCustomerId().equals(1L)).findFirst().orElse(null);
        assertNotNull(c1Response);
        assertEquals(90L, c1Response.getTotalPoints());

        CustomerRewardResponse c2Response = responses.stream().filter(r -> r.getCustomerId().equals(2L)).findFirst().orElse(null);
        assertNotNull(c2Response);
        assertEquals(20L, c2Response.getTotalPoints());
    }
}
