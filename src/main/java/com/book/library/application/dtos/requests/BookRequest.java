package com.book.library.application.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {

    @NotNull
    private String isbn;
    @NotNull
    private String name;
    @NotNull
    private String description;
}
