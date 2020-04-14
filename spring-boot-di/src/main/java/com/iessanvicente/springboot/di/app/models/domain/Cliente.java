package com.iessanvicente.springboot.di.app.models.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@RequestScope
public class Cliente {
	@Value("${cliente.nombre}")
	private String nombre;
	@Value("${cliente.apellido}")
	private String apellidos;
}
