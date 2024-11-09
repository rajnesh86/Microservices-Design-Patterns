package com.microservicedesign.product.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/product")
public class ProductServiceController {

    @Autowired
    private Environment environment;

    @PostMapping
    public String createProduct() {
        return "Post: Create Product";
    }

    @GetMapping
    public String getProduct() {
        return "Get: Get Product: " + environment.getProperty("local.server.port");
    }

    @PutMapping
    public String updateProduct() {
        return "Put: Update Product";
    }

    @DeleteMapping
    public String deleteProduct() {
        return "Delete: Delete Product";
    }
}
