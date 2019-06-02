package com.bryan.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Indexed
public class Category extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    @Column(unique = true)
    @NotBlank(message = "Enter a category")
    @Field
    private String category;

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public Category(String category) {
        this.category = category;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Category{" +
                ", books=" + books +
                ", category='" + category + '\'' +
                '}';
    }
}
