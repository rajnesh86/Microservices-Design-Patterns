package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController; // Class under test

    @Mock
    private UserService userService; // Mock of UserService

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User mockUser = new User(); // Create a mock User object
        mockUser.setId(userId);
        mockUser.setName("John Doe");

        // Stub the userService to return the mock user when getUserById is called
        when(userService.getUserById(userId)).thenReturn(mockUser);

        // Call the method to test
        User foundUser = userController.getUserById(userId);

        // Verify the result and interactions
        assertEquals(mockUser, foundUser);
        verify(userService, times(1)).getUserById(userId); // Verify that the service method was called once
    }

    @Test
    public void testCreateUser() {
        User newUser = new User();
        newUser.setName("Jane Doe");

        User createdUser = new User();
        createdUser.setId(2L);
        createdUser.setName("Jane Doe");

        // Stub the userService to return the created user when createUser is called
        when(userService.createUser(any(User.class))).thenReturn(createdUser);

        // Call the method to test
        ResponseEntity<User> response = userController.createUser(newUser);

        // Verify the result
        assertEquals(ResponseEntity.ok(createdUser), response);
        verify(userService, times(1)).createUser(newUser); // Verify that the service method was called once
    }
}
