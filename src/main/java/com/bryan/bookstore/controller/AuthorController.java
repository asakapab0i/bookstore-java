package com.bryan.bookstore.controller;

import com.bryan.bookstore.entity.Author;
import com.bryan.bookstore.service.AuthorService;
import com.bryan.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="author")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @GetMapping("/getAllAuthors")
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }

    @GetMapping("/search/{term}")
    public List<Author> searchAuthors(@PathVariable String term){
        return authorService.searchAuthor(term);
    }

    @PostMapping(value = "/author", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Author createAuthor(@RequestBody Author author){
        return authorService.createAuthor(author);
    }

    @PutMapping("/author/{author_id}")
    public Author updateAuthor(@RequestBody Author author, @PathVariable Integer author_id){
        return authorService.updateAuthor(author, author_id);
    }

    @GetMapping("/author/{author_id}")
    public Optional<Author> getAuthor(@PathVariable Integer author_id){
        return authorService.getAuthorById(author_id);
    }
}
