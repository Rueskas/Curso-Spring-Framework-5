package com.iessanvicente.springboot.webflux.app.controllers;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import com.iessanvicente.springboot.webflux.app.models.dao.IProductoDao;
import com.iessanvicente.springboot.webflux.app.models.documents.Producto;

import reactor.core.publisher.Flux;

@Controller
public class ProductoController {
	@Autowired
	private IProductoDao productoDao;
	private Logger log = LoggerFactory.getLogger(ProductoController.class);
	
	@GetMapping({"/", "/listar"})
	public String listar(Model model) {
		Flux<Producto> productos = productoDao.findAll().map(p -> {
			p.setNombre(p.getNombre().toUpperCase());
			return p;
		});
		productos.subscribe(prod -> log.info(prod.getNombre()));
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos", productos);
		return "listar";
	}
	
	@GetMapping("/listar-datadriver")
	public String listarDataDriver(Model model) {
		Flux<Producto> productos = productoDao.findAll().map(p -> {
			p.setNombre(p.getNombre().toUpperCase());
			return p;
		}).delayElements(Duration.ofSeconds(1));
		productos.subscribe(prod -> log.info(prod.getNombre()));
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos", new ReactiveDataDriverContextVariable(productos, 1));
		return "listar";
	}
	
	@GetMapping("/listar-full")
	public String listarFull(Model model) {
		Flux<Producto> productos = productoDao.findAll().map(p -> {
			p.setNombre(p.getNombre().toUpperCase());
			return p;
		}).repeat(5000);
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos", productos);
		return "listar";
	}
	@GetMapping("/listar-chuncked")
	public String listarChuncked(Model model) {
		Flux<Producto> productos = productoDao.findAll().map(p -> {
			p.setNombre(p.getNombre().toUpperCase());
			return p;
		}).repeat(5000);
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos", productos);
		return "listar-chuncked";
	}
}
