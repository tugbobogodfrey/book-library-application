package com.book.library.application.dtos.response;

import com.book.library.application.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {

    private int id;
    private String isbn;
    private String name;
    private String description;

    private Category category;

    public BookResponse(String isbn, String name, String description) {
        this.isbn = isbn;
        this.name = name;
        this.description = description;
    }

    public BookResponse(int id, String isbn, String name, String description) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.description = description;
    }
}
