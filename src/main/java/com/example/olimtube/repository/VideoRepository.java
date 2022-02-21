package com.example.olimtube.repository;

import com.example.olimtube.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findAllByCategoryId(Long id);
}

