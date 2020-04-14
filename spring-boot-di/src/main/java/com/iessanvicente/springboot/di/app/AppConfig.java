package com.iessanvicente.springboot.di.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.iessanvicente.springboot.di.app.models.domain.ItemFactura;
import com.iessanvicente.springboot.di.app.models.domain.Producto;
import com.iessanvicente.springboot.di.app.models.services.IMyService;
import com.iessanvicente.springboot.di.app.models.services.MyService;
import com.iessanvicente.springboot.di.app.models.services.MyServiceComplejo;

@Configuration
public class AppConfig {
	
	@Primary
	@Bean("miServicioSimple")
	public IMyService registrarServicio() {
		return new MyService();
	}
	
	@Bean("miServicioComplejo")
	public IMyService registrarServicioComplejo() {
		return new MyServiceComplejo();
	}
	
	@Bean("itemsFactura")
	public List<ItemFactura> registrarItems(){
		return Arrays.asList(
				new ItemFactura(new Producto("Camara Sony", 99.99), 1),
				new ItemFactura(new Producto("Bicicleta", 150.50), 1),
				new ItemFactura(new Producto("Xiaomi Redmi Note 7", 200.0), 1),
				new ItemFactura(new Producto("Agua Solan de Cabras", 2.50), 3));
	}
	
	@Bean("itemsFacturaOficina")
	public List<ItemFactura> registrarItemsOficina(){
		return Arrays.asList(
				new ItemFactura(new Producto("Monitor LG", 299.99), 1),
				new ItemFactura(new Producto("Impresora HP", 100.0), 1),
				new ItemFactura(new Producto("Escritorio Oficina", 300.0), 1),
				new ItemFactura(new Producto("Notebook", 300.50), 3));
	}
}
