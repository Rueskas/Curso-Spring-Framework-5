package com.iessanvicente.springboot.datajpa.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iessanvicente.springboot.datajpa.app.models.entities.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long> {
	@Query("select f from Factura f join fetch f.cliente c join fetch f.items i join fetch i.producto where f.id=?1")
	public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id);
}
