package com.example.springboot.domain.product;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "products")
@Entity(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of  = "id")
public class Product extends RepresentationModel<Product> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private BigDecimal price;

    public Product(ProductRequestDto data){
        this.name = data.name();
        this.price = data.price();
    }

}
