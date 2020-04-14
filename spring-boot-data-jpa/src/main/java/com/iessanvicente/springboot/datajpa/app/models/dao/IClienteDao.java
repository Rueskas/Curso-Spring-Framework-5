package com.iessanvicente.springboot.datajpa.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iessanvicente.springboot.datajpa.app.models.entities.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long>{

}
