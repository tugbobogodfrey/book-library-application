package com.book.library.application.services;

import com.book.library.application.controllers.BookController;
import com.book.library.application.controllers.CategoryController;
import com.book.library.application.dtos.requests.BookRequest;
import com.book.library.application.dtos.requests.CategoryRequest;
import com.book.library.application.dtos.response.BookResponse;
import com.book.library.application.dtos.response.CategoryResponse;
import com.book.library.application.entities.Book;
import com.book.library.application.entities.Category;
import com.book.library.application.mappers.LibraryMapper;
import com.book.library.application.repositories.BookRepository;
import com.book.library.application.repositories.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@WebMvcTest(controllers = CategoryController.class)
public class CategoryControllerTest {



    @MockBean
    private CategoryService categoryService;

    @MockBean
    CategoryRepository categoryRepository;

    @MockBean
    LibraryMapper libraryMapper;


    @Autowired
    private MockMvc mockMvc;



    @Autowired
    ObjectMapper objectMapper;







    @Test
    void deleteCategory() throws Exception {

        Category category = Category.builder()
                .id(1)
                .name("Medicine")
                .description("Pathology Details")
                .build();


        Mockito.when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        mockMvc.perform(MockMvcRequestBuilders.
                        delete("/library/delete/category/1").
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());

    }


    @Test
    @DisplayName("This should check if it is a valid category")
    void addCategory() throws Exception {
        Category category = new Category("Age of Machines", "Technology");
        CategoryResponse categoryResponse = new CategoryResponse("Age of Machines", "Technology");

        when(categoryService.addCategory(Mockito.any(CategoryRequest.class))).thenReturn(categoryResponse);
        // Build post request with BookRequest payload
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/library/addCategory")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.objectMapper.writeValueAsBytes(categoryResponse));

        mockMvc.perform(builder).andExpect(status().isCreated()).andExpect(jsonPath("$.description", is("Technology")))
                .andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(categoryResponse)));

    }

    @Test
    @DisplayName("Should list all categories when making GET request to endpoint - /")
    void shouldListAllCategories() throws Exception {
        //mockMvc.perform(MockMvcRequestBuilders.get("/addBook"))
        //      .andExpect(MockMvcResultMatchers.status().is(200));

        List<CategoryResponse> categories = new ArrayList<>();
        CategoryResponse category1 = new CategoryResponse("AD23E5R98EFT3SL00", "Culture");
        CategoryResponse category2 = new CategoryResponse("O90DEPADE564W4W83", "Music");
        categories.add(category1);
        categories.add(category2);

        // Mocking out the vehicle service
        when(categoryService.getCategories()).thenReturn(categories);

        mockMvc.perform(MockMvcRequestBuilders.get("/library/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("AD23E5R98EFT3SL00"))).andExpect(jsonPath("$[0].description", is("Culture")))
                .andExpect(jsonPath("$[1].name", is("O90DEPADE564W4W83")))
                .andExpect(jsonPath("$[1].description", is("Music")));
    }




    @Test
    @DisplayName("Should check if the endpoint for updating category works well")
    void updateCategory() throws Exception {

        CategoryRequest category = new CategoryRequest("Music", "Musical Books");
        Category categoryResponse = new Category(category.getName(), category.getDescription());
        System.out.println(categoryResponse);


        when(categoryService.updateCategory(1, category)).thenReturn(
                new CategoryResponse(0,"Music", "Musical Books")

        );

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .put("/library/update/category/1", category).contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.objectMapper.writeValueAsBytes(categoryResponse));

        mockMvc.perform(builder).andExpect(status().isCreated()).andExpect(jsonPath("$.name", is("Music")))
                .andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(categoryResponse)));

    }
}