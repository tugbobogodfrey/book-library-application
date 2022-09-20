package com.book.library.application.services;

import com.book.library.application.dtos.requests.FavouriteBookRequest;
import com.book.library.application.dtos.requests.BookRequest;
import com.book.library.application.dtos.response.BookResponse;
import com.book.library.application.entities.Book;
//import com.book.library.application.exceptions.BookNotFoundException;
//import com.book.library.application.mappers.BookMapper;
import com.book.library.application.entities.Category;
import com.book.library.application.exceptions.DataNotFoundException;
import com.book.library.application.mappers.LibraryMapper;
import com.book.library.application.repositories.BookRepository;
import com.book.library.application.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements BookServiceInterface {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private LibraryMapper bookMapper;



    @Override
    public BookResponse addBook(BookRequest book){

        Book newBook = new Book(book.getIsbn(),book.getName(), book.getDescription());
        bookRepository.save(newBook);
        BookResponse bookResponse = bookMapper.mapper(newBook);
        System.out.println(bookResponse);
        return bookResponse;
    }


    @Override
    public BookResponse addFavouriteBook(FavouriteBookRequest book){

        Book newBook = new Book(book.getIsbn(),book.getName(), book.getDescription(), Boolean.TRUE);
        bookRepository.save(newBook);
        BookResponse bookResponse = bookMapper.mapper(newBook);
        return bookResponse;

    }


    @Override
    public List<Book> addBooks(List<Book> books){
        return bookRepository.saveAll(books);
    }

    @Override
    public List<BookResponse> getBooks(){
        List<Book> books = bookRepository.findAll();
        if(books.isEmpty()){
            throw new DataNotFoundException("No book records were found");
        }

        List<BookResponse> bookResponses = bookMapper.mapper(books);

        return bookResponses;
    }

    @Override
    public BookResponse updateBook(int id, BookRequest bookRequest){

        Book oldBook = null;
        Optional<Book> optionalBook = bookRepository.findById(id);
        System.out.println(optionalBook.toString());
        if(!optionalBook.isPresent()){
            throw new DataNotFoundException(String.format("Book with id %s not found", id));
        }

            oldBook = optionalBook.get();
            oldBook.setName(bookRequest.getName());
            oldBook.setDescription(bookRequest.getDescription());
            oldBook.setIsbn(bookRequest.getIsbn());
            bookRepository.save(oldBook);

            BookResponse bookResponse = bookMapper.mapper(oldBook);
             System.out.println(bookResponse);


        //BeanUtils

        return bookResponse;

    }

    @Override
    public String deleteBook(int id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(!optionalBook.isPresent()){
            throw new DataNotFoundException(String.format("Book with id %s not found", id));
        }
        bookRepository.deleteById(id);
        return "Book got deleted";
    }


    public Book addBooksToCategory(int categoryId, int bookId){

        //Category optionalCategory = categoryRepository.findById(categoryId).get();

        Category category = categoryRepository.findById(categoryId).get();
        Book book = bookRepository.findById(bookId).get();
        System.out.println(String.format("Category: %s", category));

        if (category == null) {
            throw new DataNotFoundException(String.format("Category with id %s not found", bookId));
        }

        if (book == null) {
            throw new DataNotFoundException(String.format("Book with id %s not found", bookId));
        }



        book.assignCategory(category);
        bookRepository.save(book);

        return book;

    }
}
