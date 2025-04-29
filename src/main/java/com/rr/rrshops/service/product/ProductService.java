package com.rr.rrshops.service.product;

import com.rr.rrshops.expection.ProductNotFoundExeption;
import com.rr.rrshops.model.Category;
import com.rr.rrshops.model.Product;
import com.rr.rrshops.repository.CategoryRepository;
import com.rr.rrshops.repository.ProductRepository;
import com.rr.rrshops.request.AddProductRequest;
import com.rr.rrshops.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProducts(AddProductRequest request) {
        // Check if the category exists
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet( () -> {
            // if category exists, set it as new product category
            Category newCategory = new Category(request.getCategory().getName());
            return categoryRepository.save(newCategory);
        });

        // if yes set it as new product category
        request.setCategory(category);
        // if not, create a new category
        // then create a new product
        return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest product, Category category) {

        return new Product(
                product.getName(),
                product.getDescription(),
                product.getBrand(),
                product.getPrice(),
                product.getInventory(),
                product.getCategory()
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).
                orElseThrow(
                        ()-> new ProductNotFoundExeption("Product not found"));
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
        return productRepository.findById(productId).map(
                existingProduct -> updateExistingProduct(existingProduct, request))
                        .map(productRepository::save )
                .orElseThrow(
                        () -> new ProductNotFoundExeption("Product not found")
        );
    }


    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;

    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete,
                () -> {throw new ProductNotFoundExeption("Product not found");});
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String name) {
        return productRepository.findByCategoryName(name);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByCategoryNameAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndName(String category, String name) {
        return productRepository.findByCategoryAndName(category, name);
    }

    @Override
    public List<Product> getProductsByBrandAndCategory(String brand, String category) {
        return productRepository.findByBrandAndCategory(category, brand);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBranAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
