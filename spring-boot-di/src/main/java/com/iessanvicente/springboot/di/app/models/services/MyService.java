package com.iessanvicente.springboot.di.app.models.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

//@Primary
//@Service("miServicioSimple")
public class MyService implements IMyService {
	@Override
	public String operacion() {
		return "Ejecutar algun proceso";
	}
}
