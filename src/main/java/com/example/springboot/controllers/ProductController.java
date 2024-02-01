package com.example.springboot.controllers;

import com.example.springboot.domain.product.Product;
import com.example.springboot.domain.product.ProductRequestDto;
import com.example.springboot.domain.product.ProductResponseDto;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("products")
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

//    @GetMapping
//    public ResponseEntity<Object> getOneProduct (@PathVariable(value="id") UUID id) {
//        Optional<Product> product0 = productRepository.findById(id);
//        if(product0.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
//        }
//        product0.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
//        return ResponseEntity.status(HttpStatus.OK).body(product0.get());
//    }
//
//    @PutMapping
//    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") String id,
//                                                @RequestBody @Valid ProductRequestDto productRequestDto) {
//        Optional<Product> product0 = productRepository.findById(id);
//        if (product0.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
//        }
//        var productModel = product0.get();
//        BeanUtils.copyProperties(productRequestDto, productModel);
//        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
//    }
//
//    @DeleteMapping
//    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") String id){
//        Optional<Product> product0 = productRepository.findById(id);
//        if(product0.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
//        }
//        productRepository.delete(product0.get());
//        return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessesfully.");
//    }
}