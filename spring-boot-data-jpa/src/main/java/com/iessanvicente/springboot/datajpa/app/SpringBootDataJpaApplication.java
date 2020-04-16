package com.iessanvicente.springboot.datajpa.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.iessanvicente.springboot.datajpa.app.models.services.IUploadFileService;

@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner {
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		uploadFileService.deleteAll();
		uploadFileService.init();
		
		String password = "12345";
		for(int i = 0 ; i < 2 ; i++) {
			System.out.println(passwordEncoder.encode(password));
		}
	}

}
