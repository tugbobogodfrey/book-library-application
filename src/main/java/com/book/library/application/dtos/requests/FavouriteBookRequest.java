package com.book.library.application.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavouriteBookRequest {

    @NotNull
    private String isbn;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @JsonIgnore
    private Boolean favouriteBook;

}
