package com.example.stranglerpattern.service;

import org.springframework.stereotype.Service;

@Service
public class NewCustomerService {
    public String getCustomerDetails(String customerId) {
        // Simulating the new microservice fetching customer details
        return "Customer details from the new microservice for customer ID: " + customerId;
    }
}
