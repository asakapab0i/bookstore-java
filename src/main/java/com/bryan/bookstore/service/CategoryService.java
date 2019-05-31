package com.bryan.bookstore.service;

import com.bryan.bookstore.entity.Category;
import com.bryan.bookstore.exception.ResourceNotFoundException;
import com.bryan.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category, Integer category_id){

        Optional<Category> categoryById = categoryRepository.findById(category_id);
        if (!categoryById.isPresent()){
            throw new ResourceNotFoundException("Category with id " + category_id + " does not exist.");
        }

        Category categorySave = categoryById.get();
        categorySave.setCategory(category.getCategory());
        return categoryRepository.save(categorySave);
    }
}
