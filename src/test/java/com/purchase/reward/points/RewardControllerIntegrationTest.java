package com.purchase.reward.points;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.purchase.reward.points.controller.RewardController;

@SpringBootTest
class RewardControllerIntegrationTest {

    private final RewardController rewardController;

    private MockMvc mockMvc;

    @Autowired
    public RewardControllerIntegrationTest(RewardController rewardController) {
        this.rewardController = rewardController;
    }

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(rewardController)
                .setControllerAdvice(new com.purchase.reward.points.exception.GlobalExceptionHandler())
                .build();
    }

    @Test
    void testGetAllRewards() throws Exception {
        // Checking if we can fetch all rewards (MockDataService should have pre-populated some data)
        mockMvc.perform(get("/api/rewards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3)); // 3 mock customers
    }

    @Test
    void testGetRewardForCustomer() throws Exception {
        // Customer 1: $120(90 pts), $50(0 pts), $75(25 pts), $200(250 pts) => Total ~ 365 pts (depending on mock data logic)
        mockMvc.perform(get("/api/rewards?customerId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId", is(1)))
                .andExpect(jsonPath("$.totalPoints", is(365)));
    }

    @Test
    void testGetRewardForNonExistentCustomer() throws Exception {
        mockMvc.perform(get("/api/rewards?customerId=999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No transactions found for customer: 999"));
    }
}
