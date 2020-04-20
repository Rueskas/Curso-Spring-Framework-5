package com.iessanvicente.springboot.backend.apirest.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
		try {
			cliente = clienteService.findById(id);
		} catch(DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		if(cliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(cliente);
		}
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<?> save(@Valid @RequestBody Cliente cliente, BindingResult result){
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(
					result.getFieldErrors().stream()
					.map(e -> e.getField().substring(0,1).toUpperCase()
								+e.getField().substring(1) + " " +e.getDefaultMessage())
					.collect(Collectors.toList()));
		}
		Cliente clienteGuardado = null;
		try {
			clienteGuardado = clienteService.save(cliente);
		} catch(DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		if(clienteGuardado == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteGuardado);
		}
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result,
			@PathVariable Long id){
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(
				result.getFieldErrors().stream()
				.map(e -> e.getField().substring(0,1).toUpperCase()
							+e.getField().substring(1) + " " +e.getDefaultMessage())
				.collect(Collectors.toList()));
		}
		
		if(!clienteService.exists(id)) {
			return ResponseEntity.notFound().build();
		} else {
			cliente.setId(id);
			Cliente clienteMod = null;
			try {
				 clienteMod = clienteService.save(cliente);
			} catch(DataAccessException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
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
