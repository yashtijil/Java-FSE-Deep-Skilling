package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = (BookService) context.getBean("bookService");

        bookService.performService();

        // Verify dependency injection
        if (bookService.getBookRepository() != null) {
            System.out.println("BookRepository bean injected successfully");
        } else {
            System.out.println("BookRepository bean not injected");
        }
    }
}