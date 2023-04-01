package com.group30.major.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group30.major.model.Category;
import com.group30.major.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
    public void removeCategoryById(int id) { categoryRepository.deleteById(id); }
    public Optional<Category> getCategoryById(int id) { return categoryRepository.findById(id); }
}
