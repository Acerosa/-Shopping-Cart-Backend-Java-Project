package com.rr.rrshops.repository;

import com.rr.rrshops.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String name);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);

    List<Product> findByName(String name);

    List<Product> findByBranAndName(String brand, String name);

    List<Product> findByCategoryAndName(String category, String brand);

    List<Product> findByBrandAndCategory(String category, String brand);

    Long countByBrandAndName(String brand, String name);
}
