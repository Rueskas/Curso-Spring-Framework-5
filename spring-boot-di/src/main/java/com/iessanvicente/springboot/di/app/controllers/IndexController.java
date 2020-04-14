package com.iessanvicente.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.iessanvicente.springboot.di.app.models.services.IMyService;

@Controller
public class IndexController {
	
	@Autowired
	@Qualifier("miServicioComplejo")
	private IMyService service;
	
	@GetMapping({"/", "/index", "/home"})
	public String index(Model model) {
		model.addAttribute("objeto", service.operacion());
		return "index";
	}	
}
