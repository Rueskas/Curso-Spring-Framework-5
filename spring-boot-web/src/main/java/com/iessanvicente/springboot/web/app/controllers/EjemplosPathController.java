package com.iessanvicente.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/path")
public class EjemplosPathController {
	
	@GetMapping("/string")
	public String index() {
		return "path/index";
	}
	
	@GetMapping("/string/{texto}")
	public String variables(@PathVariable String texto, Model model) {
		model.addAttribute("texto", texto);
		return "path/ver";
	}
	
	@GetMapping("/string/{texto}/{numero}")
	public String variables(@PathVariable String texto,@PathVariable Integer numero, Model model) {
		model.addAttribute("texto", texto);
		model.addAttribute("numero", numero);
		return "path/ver";
	}
}
