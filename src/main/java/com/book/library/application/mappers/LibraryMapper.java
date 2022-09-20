package com.book.library.application.mappers;

import com.book.library.application.dtos.requests.BookRequest;
import com.book.library.application.dtos.requests.CategoryRequest;
import com.book.library.application.dtos.requests.FavouriteBookRequest;
import com.book.library.application.dtos.response.BookResponse;
import com.book.library.application.dtos.response.CategoryResponse;
import com.book.library.application.entities.Book;
import com.book.library.application.entities.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("LibraryMapper")
@Mapper(componentModel = "spring")
public interface LibraryMapper {
    Book map(BookRequest bookDetails);

    BookRequest map(Book book);
    BookResponse mapper(Book book);

    Category map(CategoryRequest categoryRequest);

    CategoryRequest map(Category category);

    CategoryResponse mapper(CategoryRequest category);

    CategoryResponse mapper(Category category);

    List<Book> mapBooks(List<BookRequest> bookRequests);

    List<CategoryResponse> map(List<Category> categories);

    List<BookResponse> mapper(List<Book> books);

    BookResponse map(FavouriteBookRequest favouriteBookRequest);
}
