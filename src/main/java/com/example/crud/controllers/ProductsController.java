package com.example.crud.controllers;

import com.example.crud.domain.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository repository;

 @GetMapping
    public ResponseEntity getAllProducts() {
        var products = repository.findAll();
       return ResponseEntity.ok("All Products: " + products);
 }
}
