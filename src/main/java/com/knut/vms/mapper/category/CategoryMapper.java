package com.knut.vms.mapper.category;

import com.knut.vms.dto.category.CategoryDto;
import com.knut.vms.entity.category.Category;

public class CategoryMapper {

    public static CategoryDto mapToCategoryDto(Category category) {

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());

        return categoryDto;
    }

    public static Category mapToCategory(CategoryDto categoryDto) {

        Category category = new Category();

        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());

        return category;
    }
}
