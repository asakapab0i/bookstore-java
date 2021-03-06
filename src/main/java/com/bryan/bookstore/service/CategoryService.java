package com.bryan.bookstore.service;

import com.bryan.bookstore.entity.Category;
import com.bryan.bookstore.exception.ResourceNotFoundException;
import com.bryan.bookstore.repository.CategoryRepository;
import com.bryan.bookstore.repository.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SearchService searchService;

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public List searchCategories(String term){
        if (term.isEmpty()){
            throw new ResourceNotFoundException("The search term is blank.");
        }
        return searchService.getCategoriesByString(term);
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

    public Optional<Category> getCategory(Integer category_id){
        Optional<Category> categoryById = categoryRepository.findById(category_id);
        if (!categoryById.isPresent()){
            throw new ResourceNotFoundException("Category with id " + category_id + " does not exist.");
        }

        return categoryRepository.findById(category_id);
    }
}
