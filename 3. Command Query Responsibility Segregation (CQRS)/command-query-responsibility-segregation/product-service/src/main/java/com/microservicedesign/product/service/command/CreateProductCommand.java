package com.microservicedesign.product.service.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

/**
 * Command class used to create a new product in the system.
 * This is part of the Command side of the CQRS pattern and is handled by the aggregate.
 */
@Builder // Lombok annotation to enable the builder pattern for this class
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
public class CreateProductCommand {

    /**
     * Specifies the identifier of the aggregate that the command targets.
     * This field is used by the Axon framework to locate the correct aggregate instance.
     */
    @TargetAggregateIdentifier
    private String productId;

    // The title or name of the product
    private String title;

    // The quantity of the product in stock
    private Integer quantity;

    // The price of the product, using BigDecimal for precision
    private BigDecimal price;
}
