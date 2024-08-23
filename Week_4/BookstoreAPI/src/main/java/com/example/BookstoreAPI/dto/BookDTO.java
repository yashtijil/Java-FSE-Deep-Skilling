package com.example.BookstoreAPI.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("isbn")
    private String isbn;
}