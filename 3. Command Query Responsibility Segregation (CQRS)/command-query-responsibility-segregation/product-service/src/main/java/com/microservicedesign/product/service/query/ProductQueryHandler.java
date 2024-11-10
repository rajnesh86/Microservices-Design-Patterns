package com.microservicedesign.product.service.query;

import com.microservicedesign.product.service.core.data.ProductsRepository;
import com.microservicedesign.product.service.response.ProductResponse;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class handles query requests for product-related data.
 * It acts as the query handler for Axon Framework and interacts with the `ProductsRepository` to fetch data.
 */
@Component
public class ProductQueryHandler {

    // Repository to access product data from the database.
    private final ProductsRepository productsRepository;

    /**
     * Constructor injection for `ProductsRepository`.
     * Using `@Autowired` to inject the dependency.
     *
     * @param productsRepository The repository to interact with product data.
     */
    @Autowired
    public ProductQueryHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    /**
     * Query handler method for handling `FindProductQuery`.
     * This method is invoked when a `FindProductQuery` is sent through the Axon Query Bus.
     *
     * @param findProductQuery The query object containing the request details.
     * @return A list of `ProductResponse` objects containing product details.
     */
    @QueryHandler
    public List<ProductResponse> findProducts(FindProductQuery findProductQuery) {
        // Fetch all product entities from the repository and map them to `ProductResponse` objects.
        return productsRepository.findAll().stream()
                .map(productEntity -> {
                    // Create a new `ProductResponse` object and copy properties from `productEntity`.
                    ProductResponse productResponse = new ProductResponse();
                    BeanUtils.copyProperties(productEntity, productResponse);
                    return productResponse;
                })
                .toList(); // Convert the stream to a list.
    }
}
