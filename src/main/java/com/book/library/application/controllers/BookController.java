package com.book.library.application.controllers;

import com.book.library.application.dtos.requests.FavouriteBookRequest;
import com.book.library.application.dtos.requests.BookRequest;
import com.book.library.application.dtos.response.BookResponse;
import com.book.library.application.dtos.response.StandardAPIResponse;
import com.book.library.application.entities.Book;
import com.book.library.application.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.MethodNotAllowedException;

import javax.validation.Valid;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/library")
public class BookController {

    @Autowired
    private BookService bookService;


    @Operation(summary = "Add Book", description = "To ddd a new Book Record", tags = "Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "", content =  @Content)
    }
    )
    @PostMapping(value = "/addBook")
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookRequest book) throws Exception{

        return new ResponseEntity<BookResponse>(bookService.addBook(book), HttpStatus.CREATED);
    }



    @Operation(summary = "Add Favourite Book", description = "To add a new Favourite Book Record", tags = "Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "", content =  @Content)
    }
    )
    @PostMapping(value = "/addFavouriteBook")
    public ResponseEntity<BookResponse> addFavouriteBook(@Valid @RequestBody FavouriteBookRequest favouriteBook) throws Exception{

        return new ResponseEntity<BookResponse>(bookService.addFavouriteBook(favouriteBook), HttpStatus.CREATED);
    }




    @Operation(summary = "Update Book", description = "To Update a particular Book", tags = "Put")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Book with id not found", content =  @Content)
    }
    )
    @PutMapping(value = "/update/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> editBook(@PathVariable(value = "id") int id, @Valid @RequestBody BookRequest book) {
        return new ResponseEntity<BookResponse>(bookService.updateBook(id, book), HttpStatus.CREATED);
    }



    @Operation(summary = "Get books", description = "To Get list of Books", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "", content =  @Content)
    }
    )
    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookResponse>> listBooks(){

        List<BookResponse> books = bookService.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }



    @Operation(summary = "Delete book", description = "To delete a particular Book", tags = "Delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No book records were found", content =  @Content)
    }
    )
    @DeleteMapping(value = "/delete/book/{id}")
    public ResponseEntity<StandardAPIResponse> deleteBook(@PathVariable(value = "id") int id) {
        String response = bookService.deleteBook(id);
        StandardAPIResponse standardAPIResponse = new StandardAPIResponse(
                "Book got deleted",
                HttpStatus.OK,
                new Date()
        );
        return new ResponseEntity<>(standardAPIResponse,HttpStatus.OK);
    }




    @Operation(summary = "Add Books to category", description = "To add Book to Category", tags = "Put")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Book with id not found", content =  @Content)
    }
    )
    @PutMapping(value = "/{categoryId}/books/{bookId}")
    public ResponseEntity<Book> addBooksToCategory(@PathVariable(value = "categoryId") int categoryId, @PathVariable(value = "bookId") int bookId) {



        return new ResponseEntity<Book>(bookService.addBooksToCategory(categoryId, bookId), HttpStatus.CREATED);
    }



}
