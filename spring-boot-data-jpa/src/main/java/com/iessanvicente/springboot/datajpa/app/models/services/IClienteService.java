package com.iessanvicente.springboot.datajpa.app.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iessanvicente.springboot.datajpa.app.models.entities.Cliente;

public interface IClienteService {
	List<Cliente> findAll();
	Page<Cliente> findAll(Pageable pageable);
	Cliente findById(Long id);
	void save(Cliente cliente);
	void deleteById(Long id);
}
