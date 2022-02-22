package com.example.olimtube.repository;

import com.example.olimtube.model.Category;
import com.example.olimtube.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> getCategoriesByCategoryNumber(int categoryNumber);

    List<Category> findByUserId(Long id);
}
