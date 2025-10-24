package com.ecomm.Practice.service;

import com.ecomm.Practice.model.Category;
import com.ecomm.Practice.payload.CategoryDTO;
import com.ecomm.Practice.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    String deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
