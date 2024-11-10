package com.microservicedesign.product.service.core.events;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Represents an event indicating that a new product has been created.
 * This event is used in the CQRS pattern to update the read model and synchronize the aggregate state.
 */
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
public class ProductCreatedEvent {

    // Unique identifier for the product
    private String productId;

    // The title or name of the product
    private String title;

    // The quantity of the product in stock
    private Integer quantity;

    // The price of the product, using BigDecimal for precision
    private BigDecimal price;
}
