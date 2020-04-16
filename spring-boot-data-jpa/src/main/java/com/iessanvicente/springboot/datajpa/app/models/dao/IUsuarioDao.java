package com.iessanvicente.springboot.datajpa.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iessanvicente.springboot.datajpa.app.models.entities.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	//Con Query Methods
	public Usuario findByUsername(String username);
	
	//Con Query
	@Query("select u from Usuario u where u.username = ?1")
	public Usuario getUserWithUsername(String username);
}
