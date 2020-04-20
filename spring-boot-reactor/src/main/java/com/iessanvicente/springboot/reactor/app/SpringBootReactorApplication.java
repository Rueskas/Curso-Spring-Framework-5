package com.iessanvicente.springboot.reactor.app;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iessanvicente.springboot.reactor.app.models.Usuario;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootReactorApplication implements CommandLineRunner{

	private static Logger log = LoggerFactory.getLogger(SpringBootReactorApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<String> nombresList = Arrays.asList("Sergio Rueskas", "Belén Rueskas", "Andrés Guzmán", "Fernando Costa");
		
		//Flux<String> nombres = Flux.just("Sergio Rueskas", "Belén Rueskas", "Andrés Guzmán", "Fernando Costa");
		Flux<String> nombres = Flux.fromIterable(nombresList);
		
		//El observable es inmutable
		Flux<Usuario> usuarios = nombres.
				map(nombre -> new Usuario(nombre.toUpperCase().split(" ")[0], nombre.toUpperCase().split(" ")[1]))
				.filter(usu -> usu.getNombre().contains("O"))
				.doOnNext(usuario -> {
					if(usuario.getNombre().isEmpty()) {
						throw new RuntimeException("Nombres no pueden estar vacio");
					}
					System.out.println(usuario);
				})
				.map(usuario -> {
					usuario.setNombre(usuario.getNombre().toLowerCase());
					return usuario;
					});
		
		nombres.subscribe(
				usu -> log.info(usu.toString()),
				error -> log.error(error.getMessage()),
				new Runnable() {
					@Override
					public void run() {
						log.info("Proceso terminado");
						
					}
					
				});
		usuarios.subscribe(
				usu -> log.info(usu.toString()),
				error -> log.error(error.getMessage()),
				new Runnable() {
					@Override
					public void run() {
						log.info("Proceso terminado");
						
					}
					
				});
	}

}
