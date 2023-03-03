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

import com.example.fotoalbum.model.Category;
import com.example.fotoalbum.repository.CategoryRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	private @Autowired CategoryRepository categoryRepository;

	@GetMapping
	public String index(Model model) {
		List<Category> c = categoryRepository.findAll();
		model.addAttribute("categorys", c);
		return "category/index";
 	} 
	
	@GetMapping("/create")
	public String create(Model model) {
		Category c = new Category();
		
		model.addAttribute("category", c);
		return "category/create";
	}
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "category/create";
		}
		
		categoryRepository.save(formCategory);
		return "redirect:/category";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Optional<Category> c = categoryRepository.findById(id);
		
		if (c.isEmpty()) {
			return "redirect:errors/error";
		}
		
		model.addAttribute("category", c.get());
		return "category/edit";
	}
	
	
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "category/edit";
		}
		
		categoryRepository.save(formCategory);
		return "redirect:/category";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		categoryRepository.deleteById(id);
		return "redirect:/category";
	}
	
	
	
	
}




