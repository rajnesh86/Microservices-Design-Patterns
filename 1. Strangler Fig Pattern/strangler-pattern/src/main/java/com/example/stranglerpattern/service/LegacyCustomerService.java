package com.example.stranglerpattern.service;

import org.springframework.stereotype.Service;

@Service
public class LegacyCustomerService {
    public String getCustomerDetails(String customerId) {
        // Simulating the legacy system fetching customer details
        return "Customer details from the legacy system for customer ID: " + customerId;
    }
}
