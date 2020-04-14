package com.iessanvicente.springboot.di.app.models.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

//@Service("miServicioComplejo")
public class MyServiceComplejo implements IMyService {
	@Override
	public String operacion() {
		return "Ejecutar algun proceso complejo";
	}
}
