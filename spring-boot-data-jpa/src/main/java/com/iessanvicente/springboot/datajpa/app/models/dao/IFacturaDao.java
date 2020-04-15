package com.iessanvicente.springboot.datajpa.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.iessanvicente.springboot.datajpa.app.models.entities.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long> {

}
