package com.microservicedesign.product.service.core.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ProductEntity class represents the entity model for the 'products' table in the database.
 * This class uses JPA annotations to map the class properties to the corresponding table columns.
 * The Lombok @Data annotation generates boilerplate code such as getters, setters, equals, hashCode, and toString methods.
 */
@Entity
@Table(name = "products") // Specifies the table name in the database
@Data // Lombok's annotation to generate getters, setters, toString, equals, and hashCode methods
public class ProductEntity implements Serializable {

    // Serial version UID for Serializable class (helps during deserialization)
    private static final long serialVersionUID = 1L;

    /**
     * Primary key for the ProductEntity.
     * - @Id: Specifies the primary key of the entity.
     * - @Column: Maps the field to the 'product_id' column in the database.
     * - unique: Ensures the product ID is unique in the database.
     * - nullable: Specifies that this column cannot be null.
     */
    @Id
    @Column(name = "product_id", unique = true)
    private String productId;

    /**
     * Product title.
     * - @Column: Maps the field to the 'title' column in the database.
     * - unique: Ensures the title is unique in the database (no duplicate titles).
     * - nullable: Specifies that this column cannot be null.
     */
    @Column(name = "title", unique = true)
    private String title;

    /**
     * Quantity of the product in stock.
     * - @Column is not explicitly mentioned, so the field name is used as the column name.
     * - This field represents the number of items available.
     */
    private Integer quantity;

    /**
     * Price of the product.
     * - Uses BigDecimal for precise monetary values to avoid rounding issues with floating-point types.
     */
    private BigDecimal price;
}
