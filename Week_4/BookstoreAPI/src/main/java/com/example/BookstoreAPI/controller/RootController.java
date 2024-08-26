package com.example.BookstoreAPI.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RootController {

    @GetMapping
    public CollectionModel<Object> root() {

        return CollectionModel.of(
                List.of(
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBooks(null, null)).withRel("books"),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomers(null, null)).withRel("customers")
                )
        );
    }
}