package com.book.library.application.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteBookResponse {


    private int id;
    private String isbn;
    private String name;
    private String description;
    private Boolean favouriteBook;

}
