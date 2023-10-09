package com.springboot.blog.service;

import com.springboot.blog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> listCategories();

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(long id);

    CategoryDto updateCategory(CategoryDto categoryDto, long id);

    void deleteCategory(long id);
}
