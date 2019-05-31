package com.bryan.bookstore.service;

import com.bryan.bookstore.entity.Author;
import com.bryan.bookstore.exception.ResourceNotFoundException;
import com.bryan.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

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
}
