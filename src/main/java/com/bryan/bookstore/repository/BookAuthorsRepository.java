package com.bryan.bookstore.repository;

import com.bryan.bookstore.entity.BookAuthors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorsRepository extends JpaRepository<BookAuthors, Integer> {
}
