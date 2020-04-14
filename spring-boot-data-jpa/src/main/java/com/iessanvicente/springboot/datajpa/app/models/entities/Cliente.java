package com.iessanvicente.springboot.datajpa.app.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message="Nombre no puede estar vacio")
	private String nombre;
	@NotBlank(message="Apellidos no pueden estar vacios")
	private String apellidos;
	@NotBlank(message="Email no puede estar vacio")
	@Email(message="El Email no tiene el formato correcto")
	private String email;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="La Fecha no puede estar vacio")
	private Date createAt;
	
	private String avatar;
	
	/*
	 * @PrePersist public void prePersist() { createAt = new Date(); }
	 * 
	 * /**
	 * 
	 */
	private static final long serialVersionUID = -3358279120033120991L;
}
