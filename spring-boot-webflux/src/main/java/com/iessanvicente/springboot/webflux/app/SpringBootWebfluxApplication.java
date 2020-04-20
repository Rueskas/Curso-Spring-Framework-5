package com.iessanvicente.springboot.webflux.app;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.iessanvicente.springboot.webflux.app.models.dao.IProductoDao;
import com.iessanvicente.springboot.webflux.app.models.documents.Producto;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootWebfluxApplication implements CommandLineRunner {
	@Autowired 
	private IProductoDao productoDao;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	private Logger log = LoggerFactory.getLogger(SpringBootWebfluxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection("productos").subscribe();
		Flux.just(
				new Producto("TV Panasonic Pantalla LCD", 468.56),
				new Producto("TV LG Pantalla LCD", 348.56),
				new Producto("Play Station 4", 340.99),
				new Producto("XBox One", 366.99),
				new Producto("Air Pods", 500.99)
				)
		.flatMap(producto -> {
			producto.setCreateAt(new Date());;
			return productoDao.save(producto);
		})
		.subscribe(p -> log.info(p.getId()));
	}

}
