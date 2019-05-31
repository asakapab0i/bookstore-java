package com.bryan.bookstore.controller;

import com.bryan.bookstore.entity.Category;
import com.bryan.bookstore.service.AuthorService;
import com.bryan.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bryan.bookstore.entity.Book;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="book")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @GetMapping("/getAllBooks")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/getAllBooks/{category_id}/category")
    public List<Book> getBooksByCategory(@PathVariable Integer category_id){
        return bookService.getBooksByCategory(category_id);
    }

    @GetMapping("/getAllBooks/{author_id}/author")
    public List<Book> getBooksByAuthor(){
        return bookService.getBooksByCategory();
    }

    @PostMapping(value = "/{author_id}/book/{category_id}/category", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book createBook(
            @RequestBody Book book,
            @PathVariable Integer author_id,
            @PathVariable Integer category_id){
        return bookService.createBook(book, author_id, category_id);
    }

    @GetMapping("/book/{book_id}")
    public Optional<Book> getBookById(@PathVariable Integer book_id){
        return bookService.getBookById(book_id);
    }
}
