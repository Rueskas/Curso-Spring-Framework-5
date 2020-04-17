package com.iessanvicente.springboot.jwt.app.auth.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iessanvicente.springboot.jwt.app.auth.SimpleGrantedAuthorityMixin;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements IJwtService {
	
	public static final String KEY = Base64Utils.encodeToString("U8)Rxd$8pxGQXTA-V%$K:6]%*j{UOpI/Io#Sje(4t,v*;qdZ*@^6/qmQ~)vz<Q/".getBytes());
	public static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(KEY.getBytes());
	public static final Long EXPIRATION_TIME = 360000L * 4;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	@Override
	public String create(Authentication auth) throws IOException{
		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
		Claims claims = Jwts.claims();
		claims.put("roles", new ObjectMapper().writeValueAsString(roles));
		String token = Jwts.builder()
					.setClaims(claims)
					.setSubject(auth.getName())
					.signWith(SECRET_KEY)
					.setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME ))
					.compact();
		return token;
	}

	@Override
	public boolean validate(String token) {
		Jws<Claims> claims = null;
		try {
			getClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}

	}

	@Override
	public Claims getClaims(String token) throws JwtException, IllegalArgumentException {
		JwtParser parser = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build();
		return parser.parseClaimsJws(resolve(token)).getBody();
	}

	@Override
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	@Override
	public List<SimpleGrantedAuthority> getRoles(String token)
			throws JsonParseException, JsonMappingException, IOException {

		Object roles = getClaims(token).get("roles");
		List<SimpleGrantedAuthority> authorities = Arrays
				.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
						.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
		return authorities;
	}

	@Override
	public String resolve(String token) {
		if (token != null && token.startsWith(TOKEN_PREFIX)) {
			return token.replace(TOKEN_PREFIX, "");
		} else {
			return null;
		}
	}

}
