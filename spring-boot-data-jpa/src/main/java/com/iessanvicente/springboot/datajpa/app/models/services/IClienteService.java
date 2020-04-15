package com.iessanvicente.springboot.datajpa.app.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iessanvicente.springboot.datajpa.app.models.entities.Cliente;
import com.iessanvicente.springboot.datajpa.app.models.entities.Factura;
import com.iessanvicente.springboot.datajpa.app.models.entities.Producto;

public interface IClienteService {
	List<Cliente> findAll();
	Page<Cliente> findAll(Pageable pageable);
	Cliente findById(Long id);
	void save(Cliente cliente);
	void deleteById(Long id);
	List<Producto> findProducto(String filter);
	void saveFactura(Factura factura);
	Producto findProductoById(Long id);
	Factura findFacturaById(Long id);
	void deleteFacturaById(Long id);
}
