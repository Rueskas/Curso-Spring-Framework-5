package com.iessanvicente.springboot.datajpa.app.models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iessanvicente.springboot.datajpa.app.models.dao.IUsuarioDao;
import com.iessanvicente.springboot.datajpa.app.models.entities.Usuario;

@Service
public class JpaUserDetailsService implements UserDetailsService {
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Error en el login: Usuario no encontrado.");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(usuario.getRoles().isEmpty()) {
			throw new UsernameNotFoundException("Error en el login: Usuario no tiene roles asignados.");
		}
		authorities.addAll(
				usuario.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getAuthority()))
				.collect(Collectors.toList())
				);
		System.out.println(usuario);
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true, authorities);
	}

}
