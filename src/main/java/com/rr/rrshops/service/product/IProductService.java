package com.rr.rrshops.service.product;

import com.rr.rrshops.model.Product;
import com.rr.rrshops.request.AddProductRequest;
import com.rr.rrshops.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProducts(AddProductRequest product);
    Product getProductById(Long id);
    Product updateProduct(ProductUpdateRequest request, Long productId);
    void deleteProduct(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByCategoryNameAndBrand(String category, String brand);
    List<Product> getProductsByCategoryAndName(String category, String name);
    List<Product> getProductsByBrandAndCategory(String brand, String category);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);
}

