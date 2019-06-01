package com.bryan.bookstore.controller;

import com.bryan.bookstore.entity.Category;
import com.bryan.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAllCategories")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @PostMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @PutMapping(value = "/category/{category_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category updateAuthor(@RequestBody Category category, @PathVariable Integer category_id){
        return categoryService.updateCategory(category, category_id);
    }
}
