package com.bryan.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bryan.bookstore.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
