package com.book.library.application.services;

import com.book.library.application.dtos.requests.BookRequest;
import com.book.library.application.dtos.requests.FavouriteBookRequest;
import com.book.library.application.dtos.response.BookResponse;
import com.book.library.application.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookServiceInterface {


    public BookResponse addBook(BookRequest book);
    public List<Book> addBooks(List<Book> books);

    public List<BookResponse> getBooks();

    public BookResponse updateBook(int id, BookRequest bookRequest);

    public String deleteBook(int id);

    //public Optional<Book> findBook(int id);

    public BookResponse addFavouriteBook(FavouriteBookRequest favouriteBookRequest);


}
