package com.purchase.reward.points;

import com.purchase.reward.points.controller.TransactionController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TransactionControllerIntegrationTest {

    private final TransactionController transactionController;

    private MockMvc mockMvc;

    @Autowired
    public TransactionControllerIntegrationTest(TransactionController transactionController) {
        this.transactionController = transactionController;
    }

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(transactionController)
                .setControllerAdvice(new com.purchase.reward.points.exception.GlobalExceptionHandler())
                .build();
    }

    @Test
    void testGetAllTransactions() throws Exception {
        mockMvc.perform(get("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(7));
    }

    @Test
    void testAddTransaction() throws Exception {
        String newTxJson = "{\"customerId\":5,\"amount\":150.0,\"transactionDate\":\"" + LocalDate.now().toString() + "\"}";

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTxJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId").value(5))
                .andExpect(jsonPath("$.amount").value(150.0));
    }
}
