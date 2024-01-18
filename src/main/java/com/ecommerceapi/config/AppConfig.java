package com.ecommerceapi.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
@Configuration
public class AppConfig{
	
	
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeHttpRequests(Authorize -> Authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
		.addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
		.csrf().disable()
		.cors().configurationSource(new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				
				CorsConfiguration con = new CorsConfiguration();
				con.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:4200"));
				con.setAllowedMethods(Collections.singletonList("*"));
				con.setAllowedHeaders(Arrays.asList("Authorization"));
				con.setAllowCredentials(true);
				con.setMaxAge(3600L);
			
				
				return con;
			}
		}).and().httpBasic().and().formLogin();
		
		
		return http.build();
		
		
	}

	@Bean
    public PasswordEncoder passwordEncoder(){
		
		return new BCryptPasswordEncoder();
		
	}



		
	}










