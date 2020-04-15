package com.iessanvicente.springboot.datajpa.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iessanvicente.springboot.datajpa.app.models.entities.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {
	public List<Producto> findByNombreContainsIgnoreCase(String filter);
	
	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String filter);
}
