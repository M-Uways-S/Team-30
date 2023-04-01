package com.group30.major.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group30.major.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
