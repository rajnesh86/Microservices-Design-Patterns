package com.microservicedesign.product.service.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * A response DTO (Data Transfer Object) representing the details of a product.
 * This class uses Lombok's `@Data` annotation to generate boilerplate code like getters, setters, equals, hashCode, and toString methods.
 */
@Data
public class ProductResponse {

    // Unique identifier for the product.
    private String productId;

    // The title or name of the product.
    private String title;

    // The quantity of the product available in stock.
    private Integer quantity;

    // The price of the product, represented as BigDecimal for precision (e.g., to handle currency values).
    private BigDecimal price;

    /**
     * Custom `toString` method for better readability of the ProductResponse object.
     *
     * @return A formatted string representation of the ProductResponse object.
     */
    @Override
    public String toString() {
        return "ProductResponse[productId=%s, title=%s, quantity=%d, price=%s]".formatted(productId, title, quantity, price);
    }
}
