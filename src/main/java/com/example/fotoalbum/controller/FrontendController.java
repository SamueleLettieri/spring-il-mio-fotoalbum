package com.example.fotoalbum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frontend")
public class FrontendController {

	@GetMapping
	public String index() {
		return "/frontend/index";
	}
	
	@GetMapping("/show")
	public String show()  {
		return "/frontend/show";
	}
	
}
