package com.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/").hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN")
				.requestMatchers("/new", "/save").hasAnyAuthority("ADMIN", "CREATOR")
				.requestMatchers("/edit/**", "/update/**").hasAnyAuthority("ADMIN", "EDITOR")
				.requestMatchers("/delete/**").hasAuthority("ADMIN")
				.anyRequest().authenticated())
			.formLogin(form -> form.permitAll())
			.logout(logout -> logout.permitAll())
			.exceptionHandling(ex -> ex.accessDeniedPage("/403"));
		return http.build();
	}
}
