package com.oracle.oBootMybatis01.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityConfig {
	
	@Bean
	//이거를 사용해야 암호문 => 모듈로 만들자.
	public BCryptPasswordEncoder encoderPwd() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.cors(cors -> cors.disable())
			.csrf(csrf -> csrf.disable())
			;
		
		return http.build();
	}
	
}