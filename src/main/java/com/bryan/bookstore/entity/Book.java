package com.bryan.bookstore.entity;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Indexed
public class Book extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    @Field
    private String title;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    @Field
    private String subtitle;

    @NotNull
    @Size(max = 250)
    @Field
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    public Book() {}

    public Book(@NotNull @Size(max = 100) String title,  @NotNull @Size(max = 100) String subtitle, @NotNull @Size(max = 250) String description) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
    }

    public Book(@NotNull @Size(max = 100) String title, @NotNull @Size(max = 100) String subtitle, @NotNull @Size(max = 250) String description, Author author, Category category) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.author = author;
        this.category = category;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", category=" + category +
                '}';
    }
}
