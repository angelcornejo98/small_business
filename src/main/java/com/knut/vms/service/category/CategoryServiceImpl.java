package com.knut.vms.service.category;

import com.knut.vms.dto.category.CategoryDto;
import com.knut.vms.entity.category.Category;
import com.knut.vms.entity.product.Product;
import com.knut.vms.exception.ResourceNotFoundException;
import com.knut.vms.mapper.category.CategoryMapper;
import com.knut.vms.repository.category.CategoryRepository;
import com.knut.vms.repository.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    // Create Category
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = CategoryMapper.mapToCategory(categoryDto);

        // Inserts the values in the DB
        Category savedCategory = categoryRepository.save(category);

        return CategoryMapper.mapToCategoryDto(savedCategory);
    }

    // Get a Category
    @Override
    public CategoryDto getCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow( () ->
                        new ResourceNotFoundException("Category doesn't exist with the given id: " + categoryId));

        return CategoryMapper.mapToCategoryDto(category);
    }

    // GET All Categories
    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

//        return categories.stream().map((category) -> CategoryMapper.mapToCategoryDto(category))
//                .collect(Collectors.toList());

        return categories.stream().map(CategoryMapper::mapToCategoryDto)
                .collect(Collectors.toList());

    }

    // UPDATE Category
    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto updatedCategory) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category doesn't exist with given id: " + categoryId)
        );

        category.setName(updatedCategory.getName());

        Category updatedCategoryObj = categoryRepository.save(category);

        return CategoryMapper.mapToCategoryDto(updatedCategoryObj);
    }


    @Override
    public void deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                        .orElseThrow( () -> new ResourceNotFoundException("Category doesn't exist with the given id: " + categoryId)
                        );

        List<Product> products = productRepository.findByCategoryId(categoryId);

        for(Product product : products) {
            product.setCategory(null);
        }

        productRepository.saveAll(products);

        categoryRepository.delete(category);
    }
}
