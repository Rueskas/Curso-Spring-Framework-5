package com.iessanvicente.springboot.backend.apirest.models.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessanvicente.springboot.backend.apirest.models.dao.IClienteDao;
import com.iessanvicente.springboot.backend.apirest.models.entities.Cliente;

@Service
public class clienteServiceImpl implements IClienteService {
	@Autowired
	private IClienteDao clienteDao;
	@Override
	public List<Cliente> findAll() {
		return clienteDao.findAll();
	}

	@Override
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	public void deleteById(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	public boolean exists(Long id) {
		return clienteDao.existsById(id);
	}

}
