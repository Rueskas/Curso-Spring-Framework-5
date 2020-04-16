package com.iessanvicente.springboot.datajpa.app.models.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(length = 45, unique = true)
	private String username;
	@Column(length=60)
	private String password;
	@Column(columnDefinition="boolean default true")
	private boolean enabled = true;
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="usuario")
	private Set<Role> roles;
	
	public void addRole(Role role) {
		role.setUsuario(this);
		roles.add(role);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 3281740802990539536L;

}
