package com.example.olimtube.repository;

import com.example.olimtube.model.UserCatecory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCatecoryRepository extends JpaRepository<UserCatecory, Long> {
    List<UserCatecory> findAllByUserId(Long id);
}
