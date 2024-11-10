package com.microservicedesign.product.service.query.rest;

import com.microservicedesign.product.service.query.FindProductQuery;
import com.microservicedesign.product.service.response.ProductResponse;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for handling product-related query requests.
 * It exposes an API endpoint to fetch the list of products using Axon Framework's Query Gateway.
 */
@RestController
@RequestMapping("/v1/product")
public class ProductsQueryController {

    // The QueryGateway is used to send queries to the Axon Query Bus.
    @Autowired
    private QueryGateway queryGateway;

    /**
     * API endpoint to get the list of products.
     * Sends a `FindProductQuery` through the Query Gateway and waits for the response.
     *
     * @return A list of `ProductResponse` objects containing product details.
     */
    @GetMapping
    public List<ProductResponse> getProducts() {
        // Create an instance of `FindProductQuery` to send as the query request.
        FindProductQuery findProductQuery = new FindProductQuery();

        // Send the query using QueryGateway and specify the expected response type as a list of `ProductResponse`.
        List<ProductResponse> productResponses = queryGateway.query(
                findProductQuery,
                ResponseTypes.multipleInstancesOf(ProductResponse.class)
        ).join(); // Using `.join()` to wait for the query response synchronously.

        // Return the list of product responses.
        return productResponses;
    }
}
