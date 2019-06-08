package com.bryan.bookstore.controller;

import com.bryan.bookstore.entity.Author;
import com.bryan.bookstore.entity.Category;
import com.bryan.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="category")
@CrossOrigin(origins = "${application.crossorigin.url}")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAllCategories")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/search/{term}")
    public List<Author> searchCategories(@PathVariable String term){
        return categoryService.searchCategories(term);
    }

    @PostMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @GetMapping("/category/{category_id}")
    public Optional<Category> categoryById(@PathVariable Integer category_id){
        return categoryService.getCategory(category_id);
    }

    @PutMapping(value = "/category/{category_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category updateAuthor(@RequestBody Category category, @PathVariable Integer category_id){
        return categoryService.updateCategory(category, category_id);
    }
}
