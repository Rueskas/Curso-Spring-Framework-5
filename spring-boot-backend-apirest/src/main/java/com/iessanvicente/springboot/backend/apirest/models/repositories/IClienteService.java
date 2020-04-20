package com.iessanvicente.springboot.backend.apirest.models.repositories;

import java.util.List;

import com.iessanvicente.springboot.backend.apirest.models.entities.Cliente;

public interface IClienteService {
	public List<Cliente> findAll();
	Cliente findById(Long id);
	Cliente save(Cliente cliente);
	void deleteById(Long id);
	boolean exists(Long id);
}
