package com.example.fotoalbum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fotoalbum.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
