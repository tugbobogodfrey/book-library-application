package com.book.library.application.controllers;

import com.book.library.application.dtos.requests.BookRequest;
import com.book.library.application.dtos.requests.CategoryRequest;
import com.book.library.application.dtos.response.CategoryResponse;
import com.book.library.application.dtos.response.StandardAPIResponse;
import com.book.library.application.entities.Book;
import com.book.library.application.entities.Category;
import com.book.library.application.services.BookService;
import com.book.library.application.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;




    @Operation(summary = "Add Category", description = "Add a new Category Record", tags = "Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "", content =  @Content)
    }
    )
    @PostMapping(value = "/addCategory")
    public ResponseEntity<CategoryResponse> addCategory(@Valid @RequestBody CategoryRequest category) throws Exception{
        return new ResponseEntity<CategoryResponse>(categoryService.addCategory(category), HttpStatus.CREATED);
    }


    @Operation(summary = "Update Category", description = "Update a particular Category", tags = "Put")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Category with id not found", content =  @Content)
    }
    )
    @PutMapping(value = "/update/category/{id}")
    public ResponseEntity<CategoryResponse> editCategory(@PathVariable(value = "id") int id, @Valid @RequestBody CategoryRequest category) throws Exception{

        return new ResponseEntity<CategoryResponse>(categoryService.updateCategory(id, category), HttpStatus.CREATED);
    }

    @Operation(summary = "Get Categories", description = "Get a list of Categories", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "", content =  @Content)
    }
    )
    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryResponse>> listBooks(){

        List<CategoryResponse> categories = categoryService.getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

   

    @Operation(summary = "Delete Category", description = "Delete a particular Category", tags = "Delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Category with id not found", content =  @Content)
    }
    )
    @DeleteMapping(value = "/delete/category/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable(value = "id") int id) {
         String response = categoryService.deleteCategory(id);
        StandardAPIResponse standardAPIResponse = new StandardAPIResponse(
                "Category got deleted",
                HttpStatus.OK,
                new Date()
        );
        return new ResponseEntity<>(standardAPIResponse, HttpStatus.OK);
    }


       






}
