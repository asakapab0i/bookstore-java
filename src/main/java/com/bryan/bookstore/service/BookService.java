package com.bryan.bookstore.service;

import com.bryan.bookstore.entity.Author;
import com.bryan.bookstore.entity.Book;
import com.bryan.bookstore.entity.Category;
import com.bryan.bookstore.exception.ResourceNotFoundException;
import com.bryan.bookstore.repository.AuthorRepository;
import com.bryan.bookstore.repository.BookRepository;
import com.bryan.bookstore.repository.BookSearchService;
import com.bryan.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookSearchService bookSearch;

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Integer id){
        if (!bookRepository.existsById(id)){
            throw new ResourceNotFoundException("Book with id " + id + " not found");
        }
        return bookRepository.findById(id);
    }

    public List<Book> getBooksByCategory(Integer category_id){
        Set<Book> books;

        Optional<Category> categoryById = categoryRepository.findById(category_id);
        if(!categoryById.isPresent()){
            throw new ResourceNotFoundException("Category with id " + category_id + " does not exist.");
        }

        books = categoryById.get().getBooks();
        return new ArrayList<>(books);
    }

    public List<Book> getBooksByAuthor(Integer author_id){
        Set<Book> books;

        Optional<Author> authorById = authorRepository.findById(author_id);
        if(!authorById.isPresent()){
            throw new ResourceNotFoundException("Author with id " + author_id + " does not exist.");
        }

        books = authorById.get().getBooks();
        return new ArrayList<>(books);
    }

    public Book createBook(Book book, Integer author_id, Integer category_id){
        Set<Book> books = new HashSet<>();
        Author author1 = new Author();
        Category category1 = new Category();

        Optional<Author> authorById = authorRepository.findById(author_id);
        if (!authorById.isPresent()){
            throw new ResourceNotFoundException("Author with id " + author_id + " does not exist.");
        }

        Optional<Category> categoryById = categoryRepository.findById(category_id);
        if (!categoryById.isPresent()){
            throw new ResourceNotFoundException("Category with id " + category_id + " does not exist.");
        }

        Author author = authorById.get();
        book.setAuthor(author);

        Category category = categoryById.get();
        book.setCategory(category);

        Book book1 = bookRepository.save(book);
        books.add(book1);
        author1.setBooks(books);
        category1.setBooks(books);
        return book1;
    }

}
