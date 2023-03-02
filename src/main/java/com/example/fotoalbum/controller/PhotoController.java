package com.example.fotoalbum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fotoalbum.model.Photo;
import com.example.fotoalbum.repository.PhotoRepository;

@Controller
@RequestMapping("/")
public class PhotoController {

	private @Autowired PhotoRepository photoRepository;
	
	@GetMapping
	public String index(Model model) {
		List<Photo> p = photoRepository.findAll();
		model.addAttribute("photos",p);
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
		
		model.addAttribute("photo", p);
		return "photo/create";
	}
	
	@PostMapping("photo/create")
	public String store(@ModelAttribute("photo") Photo formPotho, Model model) {
		photoRepository.save(formPotho);
		return "redirect:/";
	}
	
	@GetMapping("photo/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Optional<Photo> p = photoRepository.findById(id);
		
		if (p.isEmpty()) {
			return "redirect:errors/error";
		}
		
		model.addAttribute("photo", p.get());
		return "photo/edit";
	}
	
	@PostMapping("photo/edit/{id}")
	public String update(@ModelAttribute("photo") Photo formPhoto, Model model) {
		photoRepository.save(formPhoto);
		return "redirect:/";
	}
	
	@PostMapping("photo/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		photoRepository.deleteById(id);
		return "redirect:/";
	}
	
}
