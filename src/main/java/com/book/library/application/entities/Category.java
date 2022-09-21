package com.book.library.application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Category")
public class Category {



    public Category(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Category(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }


    @Id
    @SequenceGenerator(
            name = "Library_Category_Tablw_Sequence",
            sequenceName = "Library_Category_Table_Sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Library_Category_Table_Sequence"
    )
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    


    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Book> enrolledBooks = new ArrayList<>();





    public void enrollBooks(Book book){
        enrolledBooks.add(book);
    }


}
