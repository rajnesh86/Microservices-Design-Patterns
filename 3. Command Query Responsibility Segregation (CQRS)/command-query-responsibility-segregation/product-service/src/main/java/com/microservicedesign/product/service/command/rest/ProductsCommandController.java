package com.microservicedesign.product.service.command.rest;

import com.microservicedesign.product.service.command.CreateProductCommand;
import com.microservicedesign.product.service.request.ProductRequest;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST controller for handling product-related requests.
 * Provides endpoints for creating, retrieving, updating, and deleting products.
 */
@RestController
@RequestMapping("v1/product")
public class ProductsCommandController {

    // Environment object to access application properties
    private Environment environment;

    // CommandGateway to send commands in an Axon framework-based CQRS setup
    private CommandGateway commandGateway;

    /**
     * Constructor-based dependency injection of Environment and CommandGateway.
     *
     * @param environment    Environment object for accessing properties.
     * @param commandGateway CommandGateway for sending commands to Axon command handlers.
     */
    @Autowired
    public ProductsCommandController(Environment environment, CommandGateway commandGateway) {
        this.environment = environment;
        this.commandGateway = commandGateway;
    }

    /**
     * Endpoint to create a new product.
     * Accepts a ProductRequest object in the request body and sends a CreateProductCommand.
     *
     * @param productRequest The request body containing product details.
     * @return The result of the command execution, typically the product ID.
     */
    @PostMapping
    public String createProduct(@RequestBody ProductRequest productRequest) {
        // Construct CreateProductCommand using the request data and a generated UUID for the product ID
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .title(productRequest.getTitle())
                .productId(UUID.randomUUID().toString())
                .build();

        String result = null;
        try {
            // Send the command and wait for the result (synchronous call)
            result = commandGateway.sendAndWait(createProductCommand);
        } catch (CommandExecutionException e) {
            // Handle command execution-specific errors
            // (e.g., business validation failure in the command handler)
        } catch (Exception e) {
            // Handle other unexpected exceptions
        }

        return result;
    }


    /**
     * Endpoint to update an existing product.
     * Currently returns a placeholder message.
     *
     * @return A placeholder message indicating the update action.
     */
    @PutMapping
    public String updateProduct() {
        return "Put: Update Product";
    }

    /**
     * Endpoint to delete an existing product.
     * Currently returns a placeholder message.
     *
     * @return A placeholder message indicating the delete action.
     */
    @DeleteMapping
    public String deleteProduct() {
        return "Delete: Delete Product";
    }
}
