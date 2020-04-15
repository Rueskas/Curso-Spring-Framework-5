package com.iessanvicente.springboot.datajpa.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iessanvicente.springboot.datajpa.app.models.dao.IClienteDao;
import com.iessanvicente.springboot.datajpa.app.models.dao.IFacturaDao;
import com.iessanvicente.springboot.datajpa.app.models.dao.IProductoDao;
import com.iessanvicente.springboot.datajpa.app.models.entities.Cliente;
import com.iessanvicente.springboot.datajpa.app.models.entities.Factura;
import com.iessanvicente.springboot.datajpa.app.models.entities.Producto;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	@Autowired
	private IProductoDao productoDao;
	@Autowired
	private IFacturaDao facturaDao;

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

	@Override
	public List<Producto> findProducto(String filter) {
		return productoDao.findByNombreContainsIgnoreCase(filter);
	}

	@Override
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);
	}

	@Override
	public Producto findProductoById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	public void deleteFacturaById(Long id) {
		facturaDao.deleteById(id);
		
	}
	
}
