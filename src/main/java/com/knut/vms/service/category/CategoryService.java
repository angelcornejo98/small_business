package com.knut.vms.service.category;

import com.knut.vms.dto.category.CategoryDto;

import java.util.List;

public interface CategoryService {

    //Create a category
    CategoryDto createCategory(CategoryDto categoryDto);

    // Find a category
    CategoryDto getCategoryById(Long categoryId);

    // Find all categories
    List<CategoryDto> getAllCategories();

    // Update a category
    CategoryDto updateCategory(Long categoryId, CategoryDto updatedCategory);


    // Delete a category
    void deleteCategory(Long categoryId);

}
