package com.book.library.application.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {


    private int id;
    private String name;
    private String description;

    public CategoryResponse(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
