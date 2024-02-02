package com.example.springboot.repositories;

import com.example.springboot.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository <Product, String> {

}
