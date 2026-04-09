package com.purchase.reward.points.service;

import com.purchase.reward.points.dto.CustomerRewardResponse;

import java.util.List;

// service for rewards
public interface RewardService {

    // get rewards by id
    CustomerRewardResponse getRewardsByCustomerId(Long customerId);

    // get all rewards
    List<CustomerRewardResponse> getAllCustomerRewards();
}
