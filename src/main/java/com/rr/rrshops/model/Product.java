package com.rr.rrshops.model;

import com.rr.rrshops.expection.ProductNotFoundExeption;
import com.rr.rrshops.repository.ProductRepository;
import com.rr.rrshops.service.product.IProductService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private int inventory;

    public Product(String name, String description, String brand, BigDecimal price, int inventory, Category category) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.inventory = inventory;
        this.category = category;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;



}
