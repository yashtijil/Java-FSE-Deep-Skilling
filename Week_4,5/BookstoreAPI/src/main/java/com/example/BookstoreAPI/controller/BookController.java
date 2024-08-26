package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.dto.BookDTO;
import com.example.BookstoreAPI.exception.ResourceNotFoundException;
import com.example.BookstoreAPI.mapper.BookMapper;
import com.example.BookstoreAPI.model.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;


@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "Endpoints for managing books")
public class BookController {

    private final List<Book> bookList = new ArrayList<>();

    @Operation(summary = "Get all books or filter by title and author")
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

    @Operation(summary = "Get a book by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Book book = bookList.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        BookDTO bookDTO = BookMapper.INSTANCE.toDTO(book);
        bookDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(id)).withSelfRel());


        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomHeaderValue");
        return ResponseEntity.ok().headers(headers).body(bookDTO);
    }

    @Operation(summary = "Create a new book")
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomHeaderValue");

        Book book = BookMapper.INSTANCE.toEntity(bookDTO);
        bookList.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(bookDTO);
    }

    @Operation(summary = "Update a book by its ID")
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
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

    @Operation(summary = "Delete a book by its ID")
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