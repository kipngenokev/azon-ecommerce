package com.azon.products.controller;

import com.azon.products.dtos.CreateProductRequest;
import com.azon.products.dtos.ProductResponse;

import com.azon.products.entity.Product;
import com.azon.products.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody CreateProductRequest createProductRequest) {

        ProductResponse newProduct = productService.createProduct(createProductRequest);
        return ResponseEntity
                .created(URI.create("api/products" + newProduct.getId()))
                .body(newProduct);
    }

}
