package com.iessanvicente.springboot.di.app.models.domain;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@RequestScope
public class Factura {

	@Value("${factura.descripcion}")
	private String descripcion;
	@Autowired
	private Cliente cliente;
	@Autowired
	@Qualifier("itemsFacturaOficina")
	private List<ItemFactura> items;
	
	@PostConstruct
	public void init() {
		cliente.setNombre(cliente.getNombre().concat(" ").concat("xxx"));
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("Factura Destruida");
	}
}
