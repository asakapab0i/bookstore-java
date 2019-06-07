package com.bryan.bookstore.service;

import com.bryan.bookstore.entity.Author;
import com.bryan.bookstore.entity.Book;
import com.bryan.bookstore.entity.BookAuthors;
import com.bryan.bookstore.entity.Category;
import com.bryan.bookstore.exception.ResourceNotFoundException;
import com.bryan.bookstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookAuthorsRepository bookAuthorsRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SearchService searchService;

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Integer id){
        if (!bookRepository.existsById(id)){
            throw new ResourceNotFoundException("Book with id " + id + " not found");
        }

        return bookAuthorsRepository.findByBook_id(id);
        //return bookRepository.findById(id);
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

    public Set<BookAuthors> getBooksByAuthor(Integer author_id){
        Set<BookAuthors> books;

        Optional<Author> authorById = authorRepository.findById(author_id);
        if(!authorById.isPresent()){
            throw new ResourceNotFoundException("Author with id " + author_id + " does not exist.");
        }

        return authorById.get().getBookAuthors();
    }

    public Book createBook(Book book, Integer author_id, Integer category_id){

        Optional<Author> authorById = authorRepository.findById(author_id);
        if (!authorById.isPresent()){
            throw new ResourceNotFoundException("Author with id " + author_id + " does not exist.");
        }

        Optional<Category> categoryById = categoryRepository.findById(category_id);
        if (!categoryById.isPresent()){
            throw new ResourceNotFoundException("Category with id " + category_id + " does not exist.");
        }

        Author author = authorById.get();
        Category category = categoryById.get();
        book.setCategory(category);
        Book bookSaved = bookRepository.save(book);

        BookAuthors bookAuthors = new BookAuthors(bookSaved, author);
        BookAuthors bookAuthors1 = bookAuthorsRepository.save(bookAuthors);

        return bookSaved;
    }

//    public Book createBook(Book book, Integer author_id, Integer category_id){
//        Set<Book> books = new HashSet<>();
//        Author author1 = new Author();
//        Category category1 = new Category();
//
//        Optional<Author> authorById = authorRepository.findById(author_id);
//        if (!authorById.isPresent()){
//            throw new ResourceNotFoundException("Author with id " + author_id + " does not exist.");
//        }
//
//        Optional<Category> categoryById = categoryRepository.findById(category_id);
//        if (!categoryById.isPresent()){
//            throw new ResourceNotFoundException("Category with id " + category_id + " does not exist.");
//        }
//
//        Author author = authorById.get();
//        book.setAuthor(author);
//
//        Category category = categoryById.get();
//        book.setCategory(category);
//
//        Book book1 = bookRepository.save(book);
//        books.add(book1);
//        author1.setBooks(books);
//        category1.setBooks(books);
//        return book1;
//    }

    public Book updateBook(Book book, Integer book_id){
        Optional<Book> bookById = bookRepository.findById(book_id);
        if (!bookById.isPresent()){
            throw new ResourceNotFoundException("Book with id " + book_id + " does not exist.");
        }

        Book bookSave = bookById.get();

        bookSave.setTitle(book.getTitle());
        bookSave.setSubtitle(book.getSubtitle());
        bookSave.setDescription(book.getDescription());

        bookSave.setBookAuthors(bookSave.getBookAuthors());

        bookSave.setCategory(bookSave.getCategory());

        bookRepository.save(bookSave);

        Optional<Book> bookById2 = bookRepository.findById(book_id);
        if (!bookById2.isPresent()){
            throw new ResourceNotFoundException("Book with id " + book_id + " does not exist.");
        }

        return bookById2.get();
    }

    public List searchBook(String term){
        if (term.isEmpty()){
            throw new ResourceNotFoundException("The search term is blank.");
        }
        return searchService.getBooksByString(term);
    }

}
