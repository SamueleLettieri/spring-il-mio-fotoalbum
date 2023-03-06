package com.example.fotoalbum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fotoalbum.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
