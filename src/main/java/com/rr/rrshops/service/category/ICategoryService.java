package com.rr.rrshops.service.category;


import com.rr.rrshops.model.Category;

import java.util.List;

public interface ICategoryService {


    Category addCategory(Category category);

    Category getCategoryById(Long id);

    Category updateCategory(Category category, Long id);

    void deleteCategory(Long id);

    Category getCategoryByName(String name);

    List<Category> getAllCategories();

}
