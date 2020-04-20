package com.iessanvicente.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iessanvicente.springboot.backend.apirest.models.entities.Cliente;

@Repository
public interface IClienteDao extends JpaRepository<Cliente, Long> {

}
