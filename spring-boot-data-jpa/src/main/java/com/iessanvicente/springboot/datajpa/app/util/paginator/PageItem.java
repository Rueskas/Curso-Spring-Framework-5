package com.iessanvicente.springboot.datajpa.app.util.paginator;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PageItem {
	private int numero;
	private boolean esActual;
}
