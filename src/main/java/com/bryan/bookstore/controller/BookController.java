package com.bryan.bookstore.controller;

import com.bryan.bookstore.entity.Book;
import com.bryan.bookstore.service.AuthorService;
import com.bryan.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="book")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/getAllBooks")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/searchBooks/{term}")
    public List searchBooks(@PathVariable String term){
        return bookService.searchBook(term);
    }

    @GetMapping("/getAllBooks/{category_id}/category")
    public List<Book> getBooksByCategory(@PathVariable Integer category_id){
        return bookService.getBooksByCategory(category_id);
    }

    @GetMapping("/getAllBooks/{author_id}/author")
    public List<Book> getBooksByAuthor(@PathVariable Integer author_id){
        return bookService.getBooksByAuthor(author_id);
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

    @PutMapping(value = "/book/{book_id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Integer book_id){
        return bookService.updateBook(book, book_id);
    }
}
