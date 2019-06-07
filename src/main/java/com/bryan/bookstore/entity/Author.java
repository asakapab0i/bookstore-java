package com.bryan.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Indexed
public class Author extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    Set<BookAuthors> bookAuthors;

    @NotBlank(message = "Enter first name")
    @Field
    private String first_name;

    @NotBlank(message = "Enter last name")
    @Field
    private String last_name;

    public Author() {
    }

    public Integer getId() {
        return id;
    }

    public Set<BookAuthors> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(Set<BookAuthors> bookAuthors){
        this.bookAuthors = bookAuthors;
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
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
