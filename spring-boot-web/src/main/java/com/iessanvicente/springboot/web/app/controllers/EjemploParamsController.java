package com.iessanvicente.springboot.web.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params")
public class EjemploParamsController {

	@GetMapping("/")
	public String index() {
		return "params/index";
	}
	
	@GetMapping("/string")
	public String param(@RequestParam(required = false, defaultValue="Valor default") String text, Model model) {
		model.addAttribute("resultado", "El texto enviado es: ".concat(text));
		return "params/ver";
	}
	@GetMapping("/mix-params")
	public String param(@RequestParam String text,@RequestParam Integer numero, Model model) {
		model.addAttribute("resultado", "El texto enviado es: ".concat(text));
		model.addAttribute("numero", "El numero enviado es: ".concat(numero.toString()));
		return "params/ver";
	}
	@GetMapping("/mix-params-request")
	public String param(HttpServletRequest request, Model model) {
		model.addAttribute("resultado", "El texto enviado es: ".concat(request.getParameter("text")));
		model.addAttribute("numero", "El numero enviado es: " + (Integer.parseInt(request.getParameter("numero"))));
		return "params/ver";
	}
}
