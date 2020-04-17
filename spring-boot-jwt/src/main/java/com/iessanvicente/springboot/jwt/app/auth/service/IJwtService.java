package com.iessanvicente.springboot.jwt.app.auth.service;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

public interface IJwtService {
	public String create(Authentication auth) throws IOException;
	public boolean validate(String token);
	public Claims getClaims(String token) throws JwtException, IllegalArgumentException ;
	public String getUsername(String token);
	public List<SimpleGrantedAuthority> getRoles(String token) throws JsonParseException, JsonMappingException, IOException;
	public String resolve(String token);
}
