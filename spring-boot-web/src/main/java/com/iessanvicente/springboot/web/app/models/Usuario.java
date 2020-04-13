package com.iessanvicente.springboot.web.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Usuario {
	@NonNull
	private String nombre;
	@NonNull
	private String apellidos;
	private String email;
}
