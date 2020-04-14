package com.iessanvicente.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping({"/index", "/", "", "/home"})
	public String home() {
		
		return "redirect:/app/index";
	}
	
}
