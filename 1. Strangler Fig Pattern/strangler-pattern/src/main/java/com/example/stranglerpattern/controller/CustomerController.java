package com.example.stranglerpattern.controller;

import com.example.stranglerpattern.service.StranglerFigProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private final StranglerFigProxy stranglerFigProxy;

    @Autowired
    public CustomerController(StranglerFigProxy stranglerFigProxy) {
        this.stranglerFigProxy = stranglerFigProxy;
    }

    // Expose API to fetch customer details
    @GetMapping("/customer/{customerId}")
    public String getCustomerDetails(@PathVariable String customerId) {
        return stranglerFigProxy.getCustomerDetails(customerId);
    }
}
