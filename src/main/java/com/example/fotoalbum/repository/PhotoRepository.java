package com.example.fotoalbum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fotoalbum.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer>{

	public List<Photo> findByTitleLike(String keyword);
	
}
