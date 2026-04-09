package com.purchase.reward.points.controller;

import com.purchase.reward.points.dto.CustomerRewardResponse;
import com.purchase.reward.points.service.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// rest controller for rewards
@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    // get all
    @GetMapping
    public ResponseEntity<List<CustomerRewardResponse>> getAllRewards() {
        List<CustomerRewardResponse> rewards = rewardService.getAllCustomerRewards();
        return ResponseEntity.ok(rewards);
    }

    // get by customer
    @GetMapping(params = "customerId")
    public ResponseEntity<CustomerRewardResponse> getRewardsByCustomerId(@RequestParam Long customerId) {
        CustomerRewardResponse reward = rewardService.getRewardsByCustomerId(customerId);
        return ResponseEntity.ok(reward);
    }
}
