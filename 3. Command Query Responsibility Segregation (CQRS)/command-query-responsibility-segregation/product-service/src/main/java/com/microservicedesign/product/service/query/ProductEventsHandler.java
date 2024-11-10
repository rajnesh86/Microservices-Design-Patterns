package com.microservicedesign.product.service.query;

import com.microservicedesign.product.service.core.data.ProductEntity;
import com.microservicedesign.product.service.core.data.ProductsRepository;
import com.microservicedesign.product.service.core.events.ProductCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * ProductEventsHandler handles events related to the Product entity.
 * This class listens for domain events (e.g., ProductCreatedEvent) and processes them.
 * It uses Axon Framework's event handling mechanism to update the query side of the application.
 */
@Component // Marks the class as a Spring component, making it eligible for dependency injection
public class ProductEventsHandler {

    // Repository for performing database operations on ProductEntity
    private final ProductsRepository productsRepository;

    /**
     * Constructor for ProductEventsHandler.
     * - The repository is injected via constructor injection (recommended for required dependencies).
     *
     * @param productsRepository The repository for managing ProductEntity instances.
     */
    public ProductEventsHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    /**
     * Event handler method for ProductCreatedEvent.
     * - This method is triggered when a ProductCreatedEvent is published.
     * - It creates a new ProductEntity, copies properties from the event, and saves it to the database.
     *
     * @param event The ProductCreatedEvent containing details of the newly created product.
     */
    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity existingProduct = productsRepository.findByTitle(event.getTitle());
        if (existingProduct != null) {
            // Log the warning and return without saving to avoid the duplicate error
            System.out.println("Product with title '" + event.getTitle() + "' already exists. Skipping insert.");
            return;
        }

        // Proceed with saving the new product entity
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        productsRepository.save(productEntity);
    }
}
