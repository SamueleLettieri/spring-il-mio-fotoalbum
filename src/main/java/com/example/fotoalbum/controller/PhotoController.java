package com.example.fotoalbum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fotoalbum.model.Category;
import com.example.fotoalbum.model.Photo;
import com.example.fotoalbum.repository.CategoryRepository;
import com.example.fotoalbum.repository.PhotoRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/photo")
public class PhotoController {

	private @Autowired PhotoRepository photoRepository;
	
	private @Autowired CategoryRepository categoryRepository;
	
	@GetMapping
	public String index(@RequestParam(name ="keyword", required = false) String keyword, Model model) {
		List<Photo> p;
		
		if (keyword!=null && !keyword.isEmpty()) {
			p = photoRepository.findByTitleLike("%" + keyword + "%");
		} else {
			p = photoRepository.findAll();
		}
		
		model.addAttribute("photos", p);
		return "photo/index";
	}
	
	
	@GetMapping("photo/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		Optional<Photo> p = photoRepository.findById(id);
		
		if (p.isEmpty()) {
			return "redirect:errors/error";
		}
		
		model.addAttribute("photo", p.get());
		return "photo/detail";
	}
	
	
	@GetMapping("photo/create")
	public String create(Model model) {
		Photo p = new Photo();
		List<Category> c = categoryRepository.findAll();
 		
		model.addAttribute("photo", p);
		model.addAttribute("categorys", c);
		return "photo/create";
	}
	
	@PostMapping("photo/create")
	public String store(@Valid @ModelAttribute("photo") Photo formPotho, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "photo/create";
		}
		
		photoRepository.save(formPotho);
		return "redirect:/";
	}
	
	@GetMapping("photo/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Optional<Photo> p = photoRepository.findById(id);
		
		if (p.isEmpty()) {
			return "redirect:errors/error";
		}
		
		List<Category> c = categoryRepository.findAll();
 		
		
		model.addAttribute("photo", p.get());
		model.addAttribute("categorys", c);
		return "photo/edit";
	}
	
	@PostMapping("photo/edit/{id}")
	public String update(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "photo/edit";
		}
		
		photoRepository.save(formPhoto);
		return "redirect:/";
	}
	
	@PostMapping("photo/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		photoRepository.deleteById(id);
		return "redirect:/";
	}
	
}
