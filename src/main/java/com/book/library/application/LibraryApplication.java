package com.book.library.application;

import com.book.library.application.mappers.LibraryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

	@Autowired
	LibraryMapper libraryMapper;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}
