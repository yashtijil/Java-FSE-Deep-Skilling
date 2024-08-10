package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = (BookService) context.getBean("bookService");

        // Testing
        System.out.println("BookService bean loaded: " + (bookService != null));
        System.out.println("BookRepository injected: " + (bookService.getBookRepository() != null));



    }
}