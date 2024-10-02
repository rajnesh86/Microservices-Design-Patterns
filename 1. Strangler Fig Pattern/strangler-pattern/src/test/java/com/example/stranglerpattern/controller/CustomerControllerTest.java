package com.example.stranglerpattern.controller;

import com.example.stranglerpattern.service.StranglerFigProxy;
import com.example.stranglerpattern.service.controller.CustomerController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerControllerTest {
    @Mock
    private StranglerFigProxy stranglerFigProxy;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    void getCustomerDetails_shouldCallLegacySystem() {
        // Given
        String customerId = "123";
        String expectedDetails = "Customer details from the legacy system for customer ID: 123";
        when(stranglerFigProxy.getCustomerDetails(customerId)).thenReturn(expectedDetails);

        // When
        String actualDetails = customerController.getCustomerDetails(customerId);

        // Then
        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    void getCustomerDetails_shouldCallNewMicroservice() {
        // Given
        String customerId = "NEW";
        String expectedDetails = "Customer details from the new microservice for customer ID:: NEW";
        when(stranglerFigProxy.getCustomerDetails(customerId)).thenReturn(expectedDetails);

        // When
        String actualDetails = customerController.getCustomerDetails(customerId);

        // Then
        assertEquals(expectedDetails, actualDetails);
    }
}
