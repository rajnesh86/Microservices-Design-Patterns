package com.microservicedesign.product.service.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ProductsRepository is a Spring Data JPA repository interface for managing ProductEntity instances.
 * This interface provides CRUD operations and custom query methods for interacting with the 'products' table.
 * It extends JpaRepository, which comes with many built-in methods for data manipulation.
 */
public interface ProductsRepository extends JpaRepository<ProductEntity, String> {

    /**
     * Custom query method to find a product by its productId.
     * Spring Data JPA will automatically generate the implementation based on the method name.
     *
     * @param productId The unique identifier of the product.
     * @return The ProductEntity with the specified productId, or null if not found.
     */
    ProductEntity findByProductId(String productId);

    /**
     * Custom query method to find a product by its productId and title.
     * This method returns a product that matches both the productId and the title.
     *
     * @param productId The unique identifier of the product.
     * @param title The title of the product.
     * @return The ProductEntity matching the specified productId and title, or null if not found.
     */
    ProductEntity findByProductIdAndTitle(String productId, String title);

    ProductEntity findByTitle(String title);
}
