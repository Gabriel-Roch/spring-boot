package com.example.demo.controllers;

import com.example.demo.domain.products.Product;
import com.example.demo.domain.products.ProductRepository;
import com.example.demo.domain.products.RequestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/product")
public class ProductsController {
    @Autowired
    private ProductRepository repository;
    @GetMapping
    public ResponseEntity getAllProducts(){
        var allProducsts = repository.findAll();
        return ResponseEntity.ok(allProducsts);
    }

    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable int id){
        var product = repository.findById(id);
        return  ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity registerProducts(@RequestBody @Validated RequestProduct data){
        Product product = new Product(data);
        repository.save(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProductById(@PathVariable int id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody @Validated RequestProduct data){
            Product existingProduct = repository.findById(data.id())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            existingProduct.setP_name(data.p_name());
            existingProduct.setP_price(data.p_price());

            Product saveProduct = repository.save(existingProduct);

            return ResponseEntity.ok(saveProduct);
    }
}


