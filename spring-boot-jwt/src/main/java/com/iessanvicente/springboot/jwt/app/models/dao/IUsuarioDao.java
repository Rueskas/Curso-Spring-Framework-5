package com.iessanvicente.springboot.jwt.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.iessanvicente.springboot.jwt.app.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	public Usuario findByUsername(String username);
}
