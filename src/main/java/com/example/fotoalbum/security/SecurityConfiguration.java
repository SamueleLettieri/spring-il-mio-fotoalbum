package com.example.fotoalbum.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeHttpRequests()
			.requestMatchers("/photo/edit", "/photo/edit/**", "/photo/create", "/photo/create/**", "/photo", "/photo/**" )
			.hasAuthority("ADMIN")
	
			.requestMatchers(HttpMethod.POST, "/photo/**").hasAuthority("ADMIN")
	
			.requestMatchers("/category", "/category/**").hasAuthority("ADMIN")
	
			.requestMatchers("/frontend", "/frontend/**").hasAnyAuthority("USER", "ADMIN")
	
			.requestMatchers("/**").permitAll()
			
			.and().formLogin().defaultSuccessUrl("/frontend", true).and().logout().and().exceptionHandling()
			
	
			.accessDeniedPage("/access-denied.html");

		return http.build();
		
	}
	
	@Bean
	DatabaseUserDetailsService userDetailsService() {
		return new DatabaseUserDetailsService();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		System.out.println(passwordEncoder().encode("ciccio"));

		return authProvider;
	}
	
}
