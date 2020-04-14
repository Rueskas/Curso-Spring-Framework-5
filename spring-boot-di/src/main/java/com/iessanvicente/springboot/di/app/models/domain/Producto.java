package com.iessanvicente.springboot.di.app.models.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
	private String nombre;
	private Double precio;
}
