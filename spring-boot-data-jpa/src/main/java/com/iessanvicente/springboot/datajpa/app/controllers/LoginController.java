package com.iessanvicente.springboot.datajpa.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(Model model, Principal principal, RedirectAttributes flash, 
			@RequestParam(name="error", required=false) String error) {
		if(principal != null) {
			flash.addFlashAttribute("info", "Ya has iniciado sesion anteriormente.");
			return "redirect:/app/";
		}
		if(error != null) {
			model.addAttribute("error", "Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo.");
		}
		return "login";
	}
}
