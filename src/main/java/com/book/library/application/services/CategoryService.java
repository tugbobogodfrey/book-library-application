package com.book.library.application.services;

import com.book.library.application.dtos.requests.CategoryRequest;
import com.book.library.application.dtos.response.CategoryResponse;
import com.book.library.application.entities.Book;
import com.book.library.application.entities.Category;
//import com.book.library.application.exceptions.BookNotFoundException;
import com.book.library.application.exceptions.DataNotFoundException;
import com.book.library.application.mappers.LibraryMapper;
import com.book.library.application.repositories.BookRepository;
import com.book.library.application.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceInterface{

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LibraryMapper categoryMapper;

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryDetails){

        Category category = Category.builder()
                .name(categoryDetails.getName())
                .description(categoryDetails.getDescription())
                .build();

        categoryRepository.save(category);
        CategoryResponse categoryResponse = categoryMapper.mapper(category);
        return categoryResponse;
    }

    @Override
    public List<Category> addCategories(List<Category> categories){
        return categoryRepository.saveAll(categories);
    }

    @Override
    public List<CategoryResponse> getCategories(){

        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new DataNotFoundException("No Category records were found");
        }
        List<CategoryResponse> categoryResponses = categoryMapper.map(categories);

        return categoryResponses;
    }

    @Override
    public CategoryResponse updateCategory(int id, CategoryRequest category){

        Category oldCategory = null;
        Optional<Category> optionalCategory = categoryRepository.findById(id);


        if(!optionalCategory.isPresent()){
            throw new DataNotFoundException(String.format("Category with id %s not found", id));
        }
            oldCategory = optionalCategory.get();
            oldCategory.setName(category.getName());
            oldCategory.setDescription(category.getDescription());
            categoryRepository.save(oldCategory);

        CategoryResponse categoryResponse = categoryMapper.mapper(oldCategory);
        System.out.println(String.format("Category: %s", oldCategory));
        System.out.println(categoryResponse);


        return categoryResponse;

    }

    @Override
    public String deleteCategory(int id){

        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if(!optionalCategory.isPresent()){
            throw new DataNotFoundException(String.format("Category with id %s not found", id));
        }
        categoryRepository.deleteById(id);
        return "Category got deleted";
    }

    public Optional<Category> findCategory(int id){
        return categoryRepository.findById(id);
    }

    /*public Category addBooksToCategory(int categoryId, int bookId){

        Category optionalCategory = categoryRepository.findById(categoryId).get();

        Category category = categoryRepository.findById(categoryId).get();
        Book book = bookRepository.findById(bookId).get();

        category.enrollBooks(book);

        categoryRepository.save(category);

        return category;

    } */

    public Category addBooksToCategory(int categoryId, int bookId){

        Category optionalCategory = categoryRepository.findById(categoryId).get();

        Category category = categoryRepository.findById(categoryId).get();
        Book book = bookRepository.findById(bookId).get();

        category.enrollBooks(book);

        categoryRepository.save(category);

        return category;

    }



}
