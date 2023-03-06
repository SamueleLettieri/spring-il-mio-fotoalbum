/*
package com.example.fotoalbum.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fotoalbum.model.Comment;
import com.example.fotoalbum.repository.CommentRepository;


@RestController
@CrossOrigin
@RequestMapping("/api/comment")
public class CommentRestController {

	private @Autowired CommentRepository commentRepository;
	
	@PostMapping("/create")
	public Comment create(@RequestBody Comment comment) {
		return commentRepository.save(comment);
	}
	
	
}
*/