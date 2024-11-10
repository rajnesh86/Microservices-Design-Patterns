package com.microservicedesign.product.service.command;

import com.microservicedesign.product.service.core.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * Aggregate class for handling product-related commands and events in a CQRS pattern using Axon framework.
 * Represents the domain entity and manages state changes through events.
 */
@Aggregate
public class ProductAggregate {

    // Unique identifier for the aggregate instance
    @AggregateIdentifier
    private String productId;

    // The title of the product
    private String title;

    // The quantity of the product in stock
    private Integer quantity;

    // The price of the product, using BigDecimal for precision
    private BigDecimal price;

    /**
     * Default constructor required by the Axon framework.
     */
    public ProductAggregate() {
    }

    /**
     * Command handler for creating a new product.
     * Validates the input command and applies the ProductCreatedEvent if successful.
     *
     * @param createProductCommand The command containing the details to create a product.
     */
    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        // Validate that the price is greater than zero
        if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        // Validate that the title is not blank
        if (createProductCommand.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title must not be blank");
        }

        // Create a new ProductCreatedEvent to represent the state change
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();

        // Copy properties from the command to the event object
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

        // Apply the event, which will be handled by the event sourcing handler
        AggregateLifecycle.apply(productCreatedEvent);
    }

    /**
     * Event sourcing handler for the ProductCreatedEvent.
     * Updates the aggregate's state based on the event data.
     *
     * @param productCreatedEvent The event containing the product details.
     */
    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        // Update the aggregate's fields with the data from the event
        this.productId = productCreatedEvent.getProductId();
        this.title = productCreatedEvent.getTitle();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();
    }
}
