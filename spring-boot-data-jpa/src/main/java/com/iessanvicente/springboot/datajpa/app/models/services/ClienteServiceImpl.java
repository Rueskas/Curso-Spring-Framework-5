package com.iessanvicente.springboot.datajpa.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iessanvicente.springboot.datajpa.app.models.dao.IClienteDao;
import com.iessanvicente.springboot.datajpa.app.models.entities.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;

	@Override
	public List<Cliente> findAll() {
		return clienteDao.findAll();
	}
	
	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}
	
	@Override
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
	}

	@Override
	public void deleteById(Long id) {
		clienteDao.deleteById(id);
	}
	
}
