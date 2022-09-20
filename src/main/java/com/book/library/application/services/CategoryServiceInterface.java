package com.book.library.application.services;

import com.book.library.application.dtos.requests.CategoryRequest;
import com.book.library.application.dtos.response.CategoryResponse;
import com.book.library.application.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServiceInterface {


    public CategoryResponse addCategory(CategoryRequest category);

    public List<Category> addCategories(List<Category> categories);

    public List<CategoryResponse> getCategories();

    public CategoryResponse updateCategory(int id, CategoryRequest category);

    public String deleteCategory(int id);

    public Optional<Category> findCategory(int id);

}
