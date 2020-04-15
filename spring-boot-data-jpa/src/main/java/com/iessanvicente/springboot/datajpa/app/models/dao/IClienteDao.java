package com.iessanvicente.springboot.datajpa.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iessanvicente.springboot.datajpa.app.models.entities.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long>{
	@Query("select c from Cliente c left join fetch c.facturas where c.id=?1")
	public Cliente fetchByIdWithFacturasWithItemsWithProducto(Long id);
}
