package com.example.crud.controllers;

import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.ProductRepository;
import com.example.crud.domain.product.RequestProductDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

 @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProductDTO data) {
     Product newProduct = new Product (data);
     repository.save(newProduct);
     return ResponseEntity.ok("Product created:" + data);
 }

 @PutMapping
 @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProductDTO data) {
     Optional<Product> product = repository.findById(data.id());
    if(product.isPresent()) {
        Product updatedProduct = product.get();
        updatedProduct.setName(data.name());
        updatedProduct.setPrice_in_cents(data.price_in_cents());
        return ResponseEntity.ok(updatedProduct);
    } else {
        return ResponseEntity.notFound().build();
    }

 }


}
