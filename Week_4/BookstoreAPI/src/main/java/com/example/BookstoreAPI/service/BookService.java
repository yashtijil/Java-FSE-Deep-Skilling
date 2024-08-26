package com.example.BookstoreAPI.service;

import com.example.BookstoreAPI.model.Book;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookService {

    private final Map<Long, Book> bookRepository = new HashMap<>();

    public Book getBookById(Long id) {
        return bookRepository.get(id);
    }

    public void saveBook(Book book) {
        bookRepository.put(book.getId(), book);
    }

}