package com.example.olimtube.service;

import com.example.olimtube.model.Category;
import com.example.olimtube.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(int categoryNumber) {
        Category category = new Category(categoryNumber);
        return categoryRepository.save(category);
    }
}
