package com.book.library.application;

import com.book.library.application.mappers.LibraryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Autowired
	LibraryMapper libraryMapper;

	@Test
	void contextLoads() {
	}

}
