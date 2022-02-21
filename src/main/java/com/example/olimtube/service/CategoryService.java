package com.example.olimtube.service;

import com.example.olimtube.component.CategoryInfo;
import com.example.olimtube.model.Category;
import com.example.olimtube.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryInfo categoryInfo;

    public Category createCategory(int categoryNumber) {
        Category category = new Category(categoryNumber, categoryInfo.getMap().get(categoryNumber));
            return categoryRepository.save(category);
    }

    public List<Category> getCategories(int categoryNumber) {
        return categoryRepository.getCategoriesByCategoryNumber(categoryNumber);
    }
}
