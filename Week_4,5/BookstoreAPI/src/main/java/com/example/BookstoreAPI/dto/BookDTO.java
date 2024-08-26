package com.example.BookstoreAPI.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.hateoas.RepresentationModel;

public class BookDTO extends RepresentationModel<BookDTO> {
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