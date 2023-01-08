package com.rahul.reportapi.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigrationss {
	
	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		
		http
		.authorizeHttpRequests((request)->request
				
				.antMatchers("/","/aboutus","/about","/search").permitAll()
				
				.anyRequest().authenticated()
				)
		.csrf().disable()// security is not working as expected getting 403 forwidden error it might be because of csrf issue or somthing else  if we allow permit the url then same req working ie without security
			//.formLogin();
		.httpBasic();
		return http.build();
		
	}
	
//	protected void securityFilter1(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//	}


}
