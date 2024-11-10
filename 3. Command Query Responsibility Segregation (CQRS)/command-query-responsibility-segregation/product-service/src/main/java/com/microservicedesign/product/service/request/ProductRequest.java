package com.microservicedesign.product.service.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Represents the request payload for product-related operations.
 * Contains basic information about the product such as title, quantity, and price.
 */
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
public class ProductRequest {

    // The title or name of the product
    private String title;

    // The quantity of the product available in stock
    private Integer quantity;

    // The price of the product represented as BigDecimal for precision
    private BigDecimal price;

    /**
     * Custom toString method for better readability of the ProductRequest object.
     *
     * @return A formatted string representation of the ProductRequest object.
     */
    @Override
    public String toString() {
        return "ProductRequest[title=%s, quantity=%d, price=%f]".formatted(title, quantity, price);
    }
}
