package com.iessanvicente.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iessanvicente.springboot.backend.apirest.models.entities.Cliente;
import com.iessanvicente.springboot.backend.apirest.models.repositories.clienteServiceImpl;

@RestController
@CrossOrigin(origins= {"http://localhost:4200"})
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private clienteServiceImpl clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
	}
} 
