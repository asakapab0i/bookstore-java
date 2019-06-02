package com.bryan.bookstore.service;

import com.bryan.bookstore.entity.Author;
import com.bryan.bookstore.exception.ResourceNotFoundException;
import com.bryan.bookstore.repository.AuthorRepository;
import com.bryan.bookstore.repository.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private SearchService searchService;

    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    public Author createAuthor(Author author){
        return authorRepository.save(author);
    }

    public Author updateAuthor(Author author, Integer author_id){

        Optional<Author> authorById = authorRepository.findById(author_id);
        if (!authorById.isPresent()){
            throw new ResourceNotFoundException("Author with id " + author_id + " does not exist.");
        }

        Author authorSave = authorById.get();
        authorSave.setFirst_name(author.getFirst_name());
        authorSave.setLast_name(author.getLast_name());
        return authorRepository.save(authorSave);
    }

    public List searchAuthor(String term){
        if (term.isEmpty()){
            throw new ResourceNotFoundException("The search term is blank.");
        }
        return searchService.getAuthorsByString(term);
    }

    public Optional<Author> getAuthorById(Integer author_id){
        Optional<Author> authorById = authorRepository.findById(author_id);
        if (!authorById.isPresent()){
            throw new ResourceNotFoundException("Author with id " + author_id + " does not exist.");
        }
        return authorRepository.findById(author_id);
    }
}
