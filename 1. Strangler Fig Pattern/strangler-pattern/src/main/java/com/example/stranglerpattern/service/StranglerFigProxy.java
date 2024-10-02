package com.example.stranglerpattern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StranglerFigProxy {
    private final LegacyCustomerService legacyCustomerService;
    private final NewCustomerService newCustomerService;

    @Autowired
    public StranglerFigProxy(LegacyCustomerService legacyCustomerService, NewCustomerService newCustomerService) {
        this.legacyCustomerService = legacyCustomerService;
        this.newCustomerService = newCustomerService;
    }

    public String getCustomerDetails(String customerId) {
        // Example logic: New customers with IDs starting with "NEW" will use the new microservice
        if (shouldUseNewMicroservice(customerId)) {
            return newCustomerService.getCustomerDetails(customerId);
        } else {
            return legacyCustomerService.getCustomerDetails(customerId);
        }
    }

    private boolean shouldUseNewMicroservice(String customerId) {
        // Logic to route to the new microservice based on customerId prefix
        return customerId.startsWith("NEW");
    }
}
