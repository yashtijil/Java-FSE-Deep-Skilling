package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private BookRepository bookRepository;

    // Constructor injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Setter method for BookRepository
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Getter method for BookRepository
    public BookRepository getBookRepository() {
        return bookRepository;
    }

    // Example method
    public void performService() {
        System.out.println("Service method called");
    }
}