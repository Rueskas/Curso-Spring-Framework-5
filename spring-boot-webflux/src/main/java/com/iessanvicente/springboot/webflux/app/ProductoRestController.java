package com.iessanvicente.springboot.webflux.app;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iessanvicente.springboot.webflux.app.controllers.ProductoController;
import com.iessanvicente.springboot.webflux.app.models.dao.IProductoDao;
import com.iessanvicente.springboot.webflux.app.models.documents.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

	@Autowired
	private IProductoDao productoDao;
	private Logger log = LoggerFactory.getLogger(ProductoController.class);
	
	@GetMapping
	public Flux<Producto> listar() {
		Flux<Producto> productos = productoDao.findAll().map(p -> {
			p.setNombre(p.getNombre().toUpperCase());
			return p;
		}).doOnNext(p -> log.info(p.getNombre())).delayElements(Duration.ofSeconds(2));
		
		return productos;
	}
	
	@GetMapping("/{id}")
	public Mono<Producto> find(@PathVariable String id) {
		//Flux<Producto> productos = productoDao.findAll();
		//Mono<Producto> producto = productos.filter(p -> p.getId().equals(id)).next().doOnNext(p -> log.info(p.getNombre()));
		Mono<Producto> producto = productoDao.findById(id);
		return producto;
	}
}
