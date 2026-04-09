package com.purchase.reward.points.dto;

import java.util.Map;

// dto for reward data
public class CustomerRewardResponse {

    private Long customerId;
    private Long totalPoints;
    private Map<String, Long> pointsPerMonth;

    public CustomerRewardResponse() {
    }

    public CustomerRewardResponse(Long customerId, Long totalPoints, Map<String, Long> pointsPerMonth) {
        this.customerId = customerId;
        this.totalPoints = totalPoints;
        this.pointsPerMonth = pointsPerMonth;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Long totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Map<String, Long> getPointsPerMonth() {
        return pointsPerMonth;
    }

    public void setPointsPerMonth(Map<String, Long> pointsPerMonth) {
        this.pointsPerMonth = pointsPerMonth;
    }
}
