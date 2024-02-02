package com.example.springboot.controllers;

import com.example.springboot.domain.product.Product;
import com.example.springboot.domain.product.ProductRequestDto;
import com.example.springboot.domain.product.ProductResponseDto;
import com.example.springboot.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/products")

public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid ProductRequestDto body){
        Product newProduct = new Product(body);

        this.productRepository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAllProducts(){
        List<ProductResponseDto> productList = this.productRepository.findAll().stream().map(ProductResponseDto::new).toList();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ProductResponseDto(optionalProduct.get()));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid ProductRequestDto productRequestDto) {
        Optional<Product> optionalProduct = productRepository.findById(productRequestDto.id());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productRequestDto.name());
            product.setPrice(productRequestDto.price());
            return ResponseEntity.ok(product);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
        String productName = product.getName();
        productRepository.delete(product);
        String successMessage = "Product '" + productName + "' deleted successfully.";
        return ResponseEntity.status(HttpStatus.OK).body(successMessage);
    }
}