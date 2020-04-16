package com.iessanvicente.springboot.datajpa.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.iessanvicente.springboot.datajpa.app.auth.handler.LoginSuccessHandler;
import com.iessanvicente.springboot.datajpa.app.models.services.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
		PasswordEncoder encoder = passwordEncoder();
		
		builder
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder);
		
		/*
		 * JDBC AUTHENTICATION
		builder.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(encoder)
			.usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ? ")
			.authoritiesByUsernameQuery(
					"SELECT u.username, a.authority "
					+ "FROM authorities a "
					+ "INNER JOIN users u on (a.user_id = u.id) "
					+ "WHERE u.username=?");
		
		Authentication in Memory
		UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		builder.inMemoryAuthentication()
			.withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
			.withUser(users.username("sergio").password("12345").roles("USER"));
		*/
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/app", "/css/**", "/js/**", "/images/**", "/app/clientes/**").permitAll()
				/*.antMatchers("/app/cliente/**").hasRole("USER")
				.antMatchers("/app/img/**").hasRole("USER")
				.antMatchers("/app/form/**").hasRole("ADMIN")
				.antMatchers("/app/eliminar/**").hasRole("ADMIN")
				.antMatchers("/app/factura/**").hasRole("ADMIN")*/
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login").successHandler(successHandler).permitAll()
			.and()
				.logout().permitAll()
			.and()
				.exceptionHandling().accessDeniedPage("/error_403");
	}
}
