package com.bryan.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    @NotBlank(message = "Enter first name")
    private String first_name;

    @NotBlank(message = "Enter last name")
    private String last_name;

    public Author() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", books=" + books +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}