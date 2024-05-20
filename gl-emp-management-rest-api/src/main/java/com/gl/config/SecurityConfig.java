package com.gl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	PasswordEncoder myPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationProvider myAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(myPasswordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(requests -> requests
                		.requestMatchers("/roles/**", "/users/**").permitAll()
                        .requestMatchers(HttpMethod.GET ,"/employees/**")
                        .hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/employees/**")
                        .hasAuthority("ADMIN").anyRequest().authenticated())
                .cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())
                .build();
	}

}
