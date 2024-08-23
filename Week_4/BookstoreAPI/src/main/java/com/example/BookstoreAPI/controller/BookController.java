package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.dto.BookDTO;
import com.example.BookstoreAPI.exception.ResourceNotFoundException;
import com.example.BookstoreAPI.mapper.BookMapper;
import com.example.BookstoreAPI.model.Book;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final List<Book> bookList = new ArrayList<>();

    // GET all books or filter by title and author
    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {

        List<BookDTO> filteredBooks = bookList.stream()
                .filter(book -> (title == null || book.getTitle().equalsIgnoreCase(title)) &&
                        (author == null || book.getAuthor().equalsIgnoreCase(author)))
                .map(BookMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(filteredBooks);
    }

    // GET book by ID with custom headers and status and throw ResourceNotFoundException
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Book book = bookList.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        BookDTO bookDTO = BookMapper.INSTANCE.toDTO(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomHeaderValue");
        return ResponseEntity.ok().headers(headers).body(bookDTO);
    }

    // Create a new book with custom headers and status
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomHeaderValue");

        Book book = BookMapper.INSTANCE.toEntity(bookDTO);
        bookList.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(bookDTO);
    }

    // PUT - Update book by ID
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Optional<Book> existingBook = bookList.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();

        if (existingBook.isPresent()) {
            bookList.remove(existingBook.get());
            Book updatedBook = BookMapper.INSTANCE.toEntity(bookDTO);
            updatedBook.setId(id);
            bookList.add(updatedBook);
            return ResponseEntity.ok(bookDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Optional<Book> book = bookList.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();

        if (book.isPresent()) {
            bookList.remove(book.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}