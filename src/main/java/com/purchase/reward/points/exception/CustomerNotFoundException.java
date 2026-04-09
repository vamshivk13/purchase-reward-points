package com.purchase.reward.points.exception;

// exception when customer is missing
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
