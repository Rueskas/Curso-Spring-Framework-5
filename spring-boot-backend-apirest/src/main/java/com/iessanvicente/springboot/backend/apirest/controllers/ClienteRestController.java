package com.iessanvicente.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		Cliente cliente = null;
		cliente = clienteService.findById(id);
		if(cliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(cliente);
		}
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<?> save(@RequestBody Cliente cliente){
		Cliente clienteGuardado = clienteService.save(cliente);
		if(clienteGuardado == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteGuardado);
		}
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id){
		if(!clienteService.exists(id)) {
			return ResponseEntity.notFound().build();
		} else {
			cliente.setId(id);
			Cliente clienteMod = clienteService.save(cliente);
			if(clienteMod == null) {
				return ResponseEntity.badRequest().build();
			} else {
				return ResponseEntity.ok(clienteMod);
			}
		}
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(clienteService.exists(id)) {
			clienteService.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
} 
