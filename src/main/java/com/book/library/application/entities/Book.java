package com.book.library.application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Book")
public class Book {

    @Id
    @SequenceGenerator(
            name = "Library_Book_Tablw_Sequence",
            sequenceName = "Library_Book_Table_Sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Library_Book_Table_Sequence"
    )
    private int id;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "favouriteBook")
    private Boolean favouriteBook;



    /*@JsonIgnore
    @ManyToMany(mappedBy = "enrolledBooks")
    private Set<Category> categories = new HashSet<>();*/


    //Now this for ManyToOne
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public Book(String isbn, String name, String description, Category category) {
        this.isbn = isbn;
        this.name = name;
        this.description = description;
        this.category = category;
    }


    public void assignCategory(Category category){
        this.category = category;
    }

    public Book(String isbn, String name, String description){
        this.isbn = isbn;
        this.name = name;
        this.description = description;
    }


    public Book(String isbn, String name, String description, Boolean favouriteBook){
        this.isbn = isbn;
        this.name = name;
        this.description = description;
        this.favouriteBook = favouriteBook;
    }


    public Book(int id, String isbn, String name, String description){
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.description = description;
    }


}
