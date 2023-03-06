package com.example.fotoalbum.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.fotoalbum.model.Comment;
import com.example.fotoalbum.model.Photo;
import com.example.fotoalbum.repository.CommentRepository;
import com.example.fotoalbum.repository.PhotoRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/photo")
public class PhotoRestController {

	private @Autowired PhotoRepository photoRepository;
	
	private @Autowired CommentRepository commentRepository;
	
	@GetMapping()
	public List<Photo> index(@RequestParam(name ="keyword", required = false) String keyword) {
		
		if (keyword!=null && !keyword.isEmpty()) {
			return photoRepository.findByTitleLike("%"+keyword+"%");
		} else {
			return photoRepository.findAll();
		}
		
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<Photo> detail(@PathVariable("id") Integer id){
		Optional<Photo> p = photoRepository.findById(id);
		
		if (p.isPresent()) {
			return new ResponseEntity<Photo>(p.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Photo>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@PostMapping("/{id}/comments")
	public ResponseEntity<Comment> addComment(@PathVariable("id") Integer id, @RequestBody Comment comment)  {
		Optional<Photo> pOptional = photoRepository.findById(id);
		if (pOptional.isPresent()) {
			comment.setPhoto(pOptional.get());
			commentRepository.save(comment);
			return new ResponseEntity<Comment>(comment, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
		}
	}
	
}











