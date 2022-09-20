package com.book.library.application.services;

import com.book.library.application.controllers.BookController;
import com.book.library.application.controllers.CategoryController;
import com.book.library.application.dtos.requests.BookRequest;
import com.book.library.application.dtos.requests.CategoryRequest;
import com.book.library.application.dtos.requests.FavouriteBookRequest;
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
@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {

    @MockBean
    private BookService bookService;


    @MockBean
    BookRepository bookRepository;


    @MockBean
    LibraryMapper libraryMapper;


    @Autowired
    private MockMvc mockMvc;



    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("This should check if it is a valid book")
    void addBook() throws Exception {

        BookRequest bookRequest = BookRequest.builder()
                .isbn("A098765")
                .name("Age of Machines")
                .description("Age of Machines")
                .build();

        BookResponse bookResponse = BookResponse.builder()
                .isbn("A098765")
                .name("Age of Machines")
                .description("Age of Machines")
                .build();


        when(bookService.addBook(Mockito.any(BookRequest.class))).thenReturn(bookResponse);
        // Build post request with BookRequest payload
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/library/addBook")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.objectMapper.writeValueAsBytes(bookResponse));


        mockMvc.perform(builder).andExpect(status().isCreated()).
                andExpect(jsonPath("$.isbn", is("A098765")))
                .andExpect(jsonPath("$.name", is("Age of Machines")))
                .andExpect(jsonPath("$.description", is("Age of Machines")))
                .andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(bookResponse)));

    }



    @Test
    @DisplayName("This should check the endpoint for adding Favourite Books")
    void addFavouriteBook() throws Exception {

        FavouriteBookRequest bookRequest = FavouriteBookRequest.builder()
                .isbn("A098765")
                .name("Age of Machines")
                .description("Age of Machines")
                .favouriteBook(Boolean.TRUE)
                .build();

        BookResponse bookResponse = BookResponse.builder()
                .isbn("A098765")
                .name("Age of Machines")
                .description("Age of Machines")
                .build();


        when(bookService.addFavouriteBook(Mockito.any(FavouriteBookRequest.class))).thenReturn(bookResponse);
        // Build post request with BookRequest payload
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/library/addFavouriteBook")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.objectMapper.writeValueAsBytes(bookResponse));


        mockMvc.perform(builder).andExpect(status().isCreated()).
                andExpect(jsonPath("$.isbn", is("A098765")))
                .andExpect(jsonPath("$.name", is("Age of Machines")))
                .andExpect(jsonPath("$.description", is("Age of Machines")))
                .andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(bookResponse)));

    }


    @Test
    @DisplayName("Should list all books when making GET request to endpoint - /")
    void shouldListAllBooks() throws Exception {


        List<BookResponse> books = new ArrayList<>();
        BookResponse book1 = new BookResponse("AD23E5R98EFT3SL00", "Macmillan", "A0876");
        BookResponse book2 = new BookResponse("O90DEPADE564W4W83", "UP Press", "B9876");
        books.add(book1);
        books.add(book2);

        // Mocking out the vehicle service
        when(bookService.getBooks()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/library/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].isbn", is("AD23E5R98EFT3SL00"))).andExpect(jsonPath("$[0].name", is("Macmillan")))
                .andExpect(jsonPath("$[1].isbn", is("O90DEPADE564W4W83")))
                .andExpect(jsonPath("$[1].name", is("UP Press")));
    }


    @Test
    @DisplayName("Should check if the endpoint for updating book works fine")
    void updateBook() throws Exception {

        BookRequest book = BookRequest
                .builder()
                .isbn("A0876")
                .name("UP09876543A")
                .description("Macmillan")
                .build();

        BookResponse bookResponse = BookResponse.builder()
                .id(0)
                .isbn(book.getIsbn())
                .name(book.getName())
                .description(book.getDescription())
                .build();


        when(bookService.updateBook(1, book)).thenReturn(
                new BookResponse(0,"A0876", "UP09876543A", "Macmillan")
        );

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .put("/library/update/book/1", book).contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.objectMapper.writeValueAsBytes(bookResponse));


        mockMvc.perform(builder).andExpect(status().isCreated()).andExpect(jsonPath("$.isbn", is("A0876")))
                .andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(bookResponse)));

    }


    @Test
    void deleteBook() throws Exception {

        Book book = new Book(1, "B09876", "Triumph of Faith", "Triumph of Faith");

        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        mockMvc.perform(MockMvcRequestBuilders.
                        delete("/library/delete/book//1").
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());

    }

}