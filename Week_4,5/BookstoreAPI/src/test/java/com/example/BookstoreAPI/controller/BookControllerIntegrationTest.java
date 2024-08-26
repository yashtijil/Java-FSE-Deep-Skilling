package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.service.BookService;
import com.example.BookstoreAPI.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest

@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        bookService.saveBook(new Book(1L, "Test Book", "Author"));
    }
    @Test
    public void testGetBookById() throws Exception {
        // Prepare a book for testing
        Book testBook = new Book(1L, "Test Book", "Test Author");
        bookService.saveBook(testBook);

        // Perform GET request and verify the result
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"title\":\"Test Book\",\"author\":\"Test Author\"}"));
    }
}