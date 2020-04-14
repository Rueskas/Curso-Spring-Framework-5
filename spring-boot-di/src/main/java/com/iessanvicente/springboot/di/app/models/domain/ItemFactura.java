package com.iessanvicente.springboot.di.app.models.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemFactura {
	private Producto producto;
	private int cantidad;
	
}
